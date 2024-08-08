package 백준.문제푸는중

fun main() {
    when(readln()) {
        "M" -> "MatKor"
        "W" -> "WiCys"
        "C" -> "CyKor"
        "A" -> "AlKor"
        "$" -> "\$clear"
        else -> throw IllegalArgumentException()
    }.let { println(it) }
}