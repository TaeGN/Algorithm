package book.코틀린인액션.collection

fun main() {
    val list = listOf(1, 2, 3, 4)
    val result1 = list.stream().filter { it > 2 }.map { it * it }.toList()
    val result2 = list.asSequence().filter { it > 2 }.map { it * it }.toList()
    println(result1)
    println(result2)
}