package Codeforces.Div2.Round973.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val (X, Y) = readln().split(" ").map(String::toInt)
        sb.appendLine(if (N == 0) 0 else (N - 1) / minOf(X, Y) + 1)
    }
    println(sb)
}