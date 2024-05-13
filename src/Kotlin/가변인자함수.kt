package Kotlin

fun main() {
    val array = Array(3) { 0 }
    val list = listOf(*array)
    println(list)
}