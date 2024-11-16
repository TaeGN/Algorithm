package AtCoder.ABC.ABC380.D

fun main() {
    val pow2 = LongArray(61) { 1L shl it }
    val S = readln().trim()
    val T = S.map { if (it.isLowerCase()) it.uppercaseChar() else it.lowercaseChar() }.joinToString("")
    val Q = readln().trim().toInt()
    val K = readln().trim().split(" ").map(String::toLong)
    val sb = StringBuilder()
    for (k in K) {
        var p = (k - 1) / S.length
        val q = ((k - 1) % S.length).toInt()
        var isReverse = false
        while (p > 0) {
            for (i in (pow2.size - 1) downTo 0) {
                if (pow2[i] <= p) {
                    p -= pow2[i]
                    isReverse = !isReverse
                    break
                }
            }
        }
        sb.append("${if (isReverse) T[q] else S[q]} ")
    }
    println(sb)
}