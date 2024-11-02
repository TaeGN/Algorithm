package AtCoder.ABC.ABC378.B

fun main() {
    val T = Array(readln().trim().toInt()) { readln().trim().split(" ").map(String::toInt) }
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (t, d) = readln().trim().split(" ").map(String::toInt)
        val (q, r) = T[t - 1]
        var value = (d / q * q).toLong() + r
        if (value < d) value += q
        sb.appendLine(value)
    }
    println(sb)
}