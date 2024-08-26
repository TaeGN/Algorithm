package Codeforces.Div2.Round968.A

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val N = readln().toInt()
        val S = readln()
        sb.appendLine(if (S.first() != S.last()) "YES" else "NO")
    }
    println(sb)
}