package AtCoder.ABC.ABC353.B

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val aArr = readln().trim().split(" ").map(String::toInt)
    fun result(): Int {
        var count = 0
        var k = 0
        for (a in aArr) {
            if (k + a <= K) k += a
            else {
                count++
                k = a
            }
        }
        if (k > 0) count++
        return count
    }
    println(result())
}