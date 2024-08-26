package Codeforces.Div2.Round968.B

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val N = readln().toInt()
        val arr = readln().split(" ").map(String::toInt).sorted()
        sb.appendLine(if (N % 2 == 0) arr[(N - 1) / 2 + 1] else arr[(N - 1) / 2])
    }
    println(sb)
}