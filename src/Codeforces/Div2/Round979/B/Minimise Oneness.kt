package Codeforces.Div2.Round979.B

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        sb.appendLine("1" + "0".repeat(readln().trim().toInt() - 1))
    }
    println(sb)
}