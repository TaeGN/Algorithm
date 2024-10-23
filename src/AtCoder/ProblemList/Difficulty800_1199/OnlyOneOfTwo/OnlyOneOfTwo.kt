package AtCoder.ProblemList.Difficulty800_1199.OnlyOneOfTwo

fun main() {
    fun gcd(a: Int, b: Int): Int = if (minOf(a, b) == 0) maxOf(a, b) else gcd(minOf(a, b), maxOf(a, b) % minOf(a, b))
    val (N, M, K) = readln().trim().split(" ").let { Triple(it[0].toInt(), it[1].toInt(), it[2].toLong()) }
    val gcd = gcd(N, M)
    val a = N / gcd
    val b = M / gcd
    val lcm = gcd.toLong() * a * b
    fun isPossible(value: Long): Boolean = (value / N + value / M - 2 * (value / lcm)) >= K
    fun search(start: Long = 0, end: Long = Long.MAX_VALUE shr 2): Long? {
        val mid = start + (end - start) / 2
        if (start == mid) return if (isPossible(start)) start else end
        return if (isPossible(mid)) search(start, mid)
        else search(mid + 1, end)
    }
    println(search())
}