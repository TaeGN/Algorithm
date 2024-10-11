package 백준.Silver.S4.p10845_큐

fun main() {
    val arr = IntArray(10000)
    var arrStart = 0
    var arrEnd = -1
    fun size() = arrEnd - arrStart + 1
    fun empty() = size() == 0
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val input = readln().trim().split(" ")
        when (input[0]) {
            "push" -> arr[++arrEnd] = input[1].toInt()
            "pop" -> sb.appendLine(if (empty()) -1 else arr[arrStart++])
            "size" -> sb.appendLine(size())
            "empty" -> sb.appendLine(if (empty()) 1 else 0)
            "front" -> sb.appendLine(if (empty()) -1 else arr[arrStart])
            "back" -> sb.appendLine(if (empty()) -1 else arr[arrEnd])
        }
    }
    println(sb)
}