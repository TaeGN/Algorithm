package Codeforces.KotlinHeroes.Episode11.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val D = readln().trim().split(" ").map(String::toInt)
        sb.appendLine(if (D[N - 1] - D.take(N - 1).max() == 1) D.take(N - 1).max() else "Ambiguous")
    }
    println(sb)
}