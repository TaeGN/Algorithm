package 백준.Gold.G1.p11414_LCM


fun main() {
    val (A, B) = readln().split(" ").map(String::toInt).let { maxOf(it[0], it[1]) to minOf(it[0], it[1]) }
    fun gcd(a: Int, b: Int): Int = if (minOf(a, b) == 0) maxOf(a, b) else gcd(minOf(a, b), maxOf(a, b) % minOf(a, b))
    fun lcm(a: Int, b: Int): Long = a.toLong() / gcd(a, b) * b
    fun minLcm(N: Int): Long = lcm(A + N, B + N)

    val diff = A - B
    var i = 1
    var minLcm = Long.MAX_VALUE
    var result = 1
    while (i.toLong() * i <= diff) {
        if (diff % i == 0) {
            minLcm(i - A % i).let {
                if (it < minLcm) {
                    minLcm = it
                    result = i - A % i
                }
            }
            minLcm((diff / i) - A % (diff / i)).let {
                if (it < minLcm) {
                    minLcm = it
                    result = (diff / i) - A % (diff / i)
                }
            }
        }
        i++
    }
    println(result)
}