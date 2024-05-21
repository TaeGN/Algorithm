package book.코틀린인액션

fun main() {
    val array = Array(3) { 0 }
    val list = listOf(*array)
    println(list)
}