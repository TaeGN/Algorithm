package 백준.Bronze.B4.p31656_StickyKeys

fun main() {
    val S = readln()
    val sb = StringBuilder()
    for (c in S) {
        if (sb.isNotEmpty() && sb.last() == c) continue
        sb.append(c)
    }
    println(sb)
}