package AtCoder.ABC.ABC379.C

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val X = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val list = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until M) {
        list.add(X[i] to A[i])
    }
    list.sortBy { it.first }
    fun result(): Long {
        var idx = 1L
        var result = 0L
        for ((x, a) in list) {
            if (x > idx) return -1
            result += (idx - x) * a + a.toLong() * (a - 1) / 2
            idx += a
        }
        return if (idx == (N.toLong() + 1)) result else -1
    }
    println(result())
}