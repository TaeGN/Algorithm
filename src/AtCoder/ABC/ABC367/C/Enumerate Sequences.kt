package AtCoder.ABC.ABC367.C

fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val rArr = readln().split(" ").map(String::toInt)
    val selected = IntArray(N)
    val sb = StringBuilder()
    fun result(idx: Int = 0, sum: Int = 0) {
        if (idx == N) {
            if (sum % K == 0) sb.appendLine(selected.joinToString(" "))
            return
        }
        for (i in 1..rArr[idx]) {
            selected[idx] = i
            result(idx + 1, sum + i)
        }
    }
    result()
    println(sb)
}