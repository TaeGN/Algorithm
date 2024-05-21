package kotlin.코틀린인액션

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)

fun main() {
    println("sample".lastChar)
    val sb = StringBuilder("sample")
    println(sb.toString())
    sb.lastChar = '!'
    println(sb.toString())
}