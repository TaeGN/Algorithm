package book.코틀린인액션.array

fun main() {
    val letters = Array(26) { ('A' + it).toString() }
    println(letters.joinToString(""))

    letters.forEachIndexed { index, letter -> println("$index - $letter") }
}