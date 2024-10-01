package Codeforces.KotlinHeroes.Episode11.C

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val input = readln().trim().split("+")
        var result = 0L
        for (i in input.indices) {
            result += if (i == 0 || i == input.size - 1) input[i].toLong()
            else maxOf(
                input[i].substring(0, 1).toLong() + input[i].substring(1).toLong(),
                input[i].substring(0, input[i].length - 1).toLong() + input[i].substring(input[i].length - 1).toLong()
            )
        }
        sb.appendLine(result)
    }
    println(sb)
}