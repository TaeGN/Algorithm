package Codeforces.KotlinHeroes.Practice11.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        sb.appendLine(readln().split(" ").map(String::toInt).let { "${minOf(it[0], it[1])} ${maxOf(it[0], it[1])}" })
    }
    println(sb)
}