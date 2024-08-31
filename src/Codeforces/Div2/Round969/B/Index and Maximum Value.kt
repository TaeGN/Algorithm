package Codeforces.Div2.Round969.B

fun main() {
    val T = readln().toInt()
    val sb = StringBuilder()
    repeat(T) {
        val (N, M) = readln().trim().split(" ").map(String::toInt)
        var max = readln().trim().split(" ").map(String::toInt).max()
        repeat(M) {
            val (c, l, r) = readln().trim().split(" ").let { Triple(it[0], it[1].toInt(), it[2].toInt()) }
            if (max in l..r) {
                if (c == "+") max++
                else max--
            }
            sb.append("$max ")
        }
        sb.appendLine()
    }
    println(sb)
}