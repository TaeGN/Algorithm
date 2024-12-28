package AtCoder.ABC.ABC386.C

fun main() {
    val K = readln().trim().toInt()
    val S = readln().trim()
    val T = readln().trim()
    fun result(): String {
        if (S.length == T.length) {
            var count = 0
            for (i in S.indices) {
                if (S[i] != T[i]) count++
            }
            if (count <= K) return "Yes"
        } else if (S.length - 1 == T.length) {
            var count = 0
            for (i in S.indices) {
                if (S[i] != T.getOrNull(i - count)) count++
            }
            if (count == 1) return "Yes"
        } else if (S.length + 1 == T.length) {
            var count = 0
            for (i in T.indices) {
                if (S.getOrNull(i - count) != T[i]) count++
            }
            if (count == 1) return "Yes"
        }
        return "No"
    }
    println(result())
}