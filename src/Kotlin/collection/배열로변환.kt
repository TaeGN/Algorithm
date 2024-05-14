package Kotlin.collection

fun main() {
    val strings = listOf("a","b","c")
    val stringArr = strings.toTypedArray()
    println("%s-%s-%s".format(*stringArr))
}