package Codeforces.Div2.Round979.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val N = readln().trim().toInt()
        val A = readln().trim().split(" ").map(String::toInt)
        fun result(): Int {
            if (N == 1) return 0
            return (A.max() - A.min()) * (N - 1)
        }
        sb.appendLine(result())
    }
    println(sb)
}