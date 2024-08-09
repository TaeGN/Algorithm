package 백준.Bronze.B5.p28691_정보보호학부동아리소개

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