package book.코틀린인액션

fun String.lastChar(): Char = this.get(this.length - 1)

fun main() {
    println("string".lastChar())
}
