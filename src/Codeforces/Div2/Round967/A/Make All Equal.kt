package Codeforces.Div2.Round967.A

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val n = readln().toInt()
        val maxCount = readln().split(" ").map(String::toInt).groupBy { it }.values.maxOf { it.size }
        sb.appendLine(n - maxCount)
    }
    println(sb)
}