package AtCoder.ABC.ABC349.D

fun main() {
    val pow2 = LongArray(62) { 1L shl it }
    var (L, R) = readln().trim().split(" ").map(String::toLong)
    var result = 0L
    val sb = StringBuilder()
    while (L < R) {
        var i = 0
        while (L % pow2[i + 1] == 0L && (L + pow2[i + 1]) <= R) i++
        result++
        sb.appendLine("$L ${L + pow2[i]}")
        L += pow2[i]
    }
    println(result)
    println(sb)
}