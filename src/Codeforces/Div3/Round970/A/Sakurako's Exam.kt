package Codeforces.Div3.Round970.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (a, b) = readln().trim().split(" ").map(String::toInt)
        sb.appendLine(
            when {
                a % 2 == 0 && b % 2 == 0 -> "Yes"
                a % 2 == 1 && b % 2 == 0 -> "No"
                a % 2 == 0 && b % 2 == 1 -> if (a >= 2) "Yes" else "No"
                a % 2 == 1 && b % 2 == 1 -> "No"
                else -> throw IllegalArgumentException()
            }
        )
    }
    println(sb)
}