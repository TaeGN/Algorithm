package Codeforces.Div4.Round971.C

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (X, Y, K) = readln().trim().split(" ").map(String::toInt)
        val xCount = if (X == 0) 0 else (X - 1) / K + 1
        val yCount = if (Y == 0) 0 else (Y - 1) / K + 1
        val result = maxOf(xCount, yCount) * 2 - if (xCount > yCount) 1 else 0
        sb.appendLine(result)
    }
    println(sb)
}