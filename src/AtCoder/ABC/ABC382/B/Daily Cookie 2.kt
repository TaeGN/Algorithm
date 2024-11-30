package AtCoder.ABC.ABC382.B

fun main() {
    val (N, D) = readln().trim().split(" ").map(String::toInt)
    val S = readln().trim()
    fun result(): String {
        val sb = StringBuilder()
        val count = S.count { it == '@' } - D
        var curCount = 0
        for (c in S) {
            if (c == '@' && ++curCount <= count) sb.append("@")
            else sb.append(".")
        }
        return sb.toString()
    }
    println(result())
}