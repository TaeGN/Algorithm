package Codeforces.Div4.Round971.A

import kotlin.math.abs

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (A, B) = readln().trim().split(" ").map(String::toInt)
        sb.appendLine(abs(A - B))
    }
    println(sb)
}