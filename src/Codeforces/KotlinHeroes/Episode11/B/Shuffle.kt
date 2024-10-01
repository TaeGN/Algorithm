package Codeforces.KotlinHeroes.Episode11.B

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        sb.append("1")
        for (i in N downTo 2) {
            sb.append(" $i")
        }
        sb.appendLine()
    }
    println(sb)
}