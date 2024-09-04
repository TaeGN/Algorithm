package Codeforces.Div4.Round971.B

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val matrix = Array(N) { readln().trim().toCharArray() }
        for (i in (N - 1) downTo 0) {
            sb.append("${matrix[i].indexOf('#') + 1} ")
        }
        sb.appendLine()
    }
    println(sb)
}