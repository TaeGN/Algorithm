package Codeforces.Div2.Round973.B

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toInt)
        sb.appendLine(A.sumOf { it.toLong() } - A.getOrElse(N - 2) { 0 } * 2)
    }
    println(sb)
}