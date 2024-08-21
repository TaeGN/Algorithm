package Codeforces.Div2.Round967.B

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val n = readln().toInt()
        if (n % 2 == 1) {
            var start = 1
            var end = n
            for (i in 1..n) {
                sb.appendLine(if (i % 2 == 1) start++ else end--)
            }
        } else sb.appendLine(-1)
    }
    println(sb)
}