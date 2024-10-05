package AtCoder.ABC.ABC374.B

fun main() {
    val S = readln()
    val T = readln()
    fun result(): Int {
        for (i in 0 until minOf(S.length, T.length)) {
            if (S[i] != T[i]) return (i + 1)
        }
        return if (S.length == T.length) 0 else (minOf(S.length, T.length) + 1)
    }
    println(result())
}