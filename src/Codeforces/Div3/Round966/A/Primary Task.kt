package Codeforces.Div3.Round966.A

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val str = readln()
        if (str.length >= 3
            && str.substring(0, 2).toInt() == 10
            && str[2].digitToInt() != 0
            && str.substring(2).toInt() >= 2
        ) sb.appendLine("Yes")
        else sb.appendLine("No")
    }
    println(sb)
}