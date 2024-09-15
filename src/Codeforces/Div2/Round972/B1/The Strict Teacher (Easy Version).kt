package Codeforces.Div2.Round972.B1

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M, Q) = readln().split(" ").map(String::toInt)
        val B = readln().split(" ").map(String::toInt).sorted()
        val A = readln().split(" ").map(String::toInt)
        for (a in A) {
            if (B.last() < a) sb.appendLine(N - B.last())
            else if (B.first() > a) sb.appendLine(B.first() - 1)
            else {
                val idx = B.binarySearch(a)
                if (idx >= 0) sb.appendLine(0)
                else (-idx - 1).let { sb.appendLine((B[it] - B[it - 1]) / 2) }
            }
        }
    }
    println(sb)
}