package Codeforces.Div3.Round974.C

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val A = readln().split(" ").map(String::toLong).sorted()
        val sumA = A.sumOf { it }
        if (N <= 2) sb.appendLine(-1)
        else sb.appendLine(maxOf(0, 2 * N.toLong() * A[N / 2] - sumA + 1))
    }
    println(sb)
}