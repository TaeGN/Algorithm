package AtCoder.ABC.ABC352.B

fun main() {
    val S = readln()
    val T = readln()
    val sb = StringBuilder()
    var i = 0
    for (j in T.indices) {
        if (i < S.length && S[i] == T[j]) {
            sb.append("${j + 1} ")
            i++
        }
    }
    println(sb)
}