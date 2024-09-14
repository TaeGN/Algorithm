package AtCoder.ABC.ABC371.A

fun main() {
    val (a, b, c) = readln().split(" ")
    fun result() = when {
        a == ">" && b == ">" && c == ">" -> "B"
        a == ">" && b == ">" && c == "<" -> "C"
        a == "<" && b == ">" && c == ">" -> "A"
        a == "<" && b == "<" && c == ">" -> "C"
        a == ">" && b == "<" && c == "<" -> "A"
        a == "<" && b == "<" && c == "<" -> "B"
        else -> throw IllegalArgumentException()
    }
    println(result())
}