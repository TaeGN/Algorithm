package Codeforces.Div2.Round979.C

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val N = readln().trim().toInt()
        val S = readln().trim()
        sb.appendLine(if ("11" in S || S.first() == '1' || S.last() == '1') "YES" else "NO")
    }
    println(sb)
}