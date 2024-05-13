package Kotlin

fun String.lastChar(): Char = this.get(this.length - 1)

fun main() {
    println("string".lastChar())
}
