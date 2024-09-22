package Codeforces.Div3.Round974.B

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, K) = readln().split(" ").map(String::toInt)
        val k = (K + (if (N % 2 == 1) 1 else 0)) / 2
        sb.appendLine(if (k % 2 == 0) "Yes" else "No")
    }
    println(sb)
}