package Codeforces.Div2.EducationalCodeforces.Round170.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val S = readln().trim()
        val T = readln().trim()
        var count = 0
        for (i in 0 until minOf(S.length, T.length)) {
            if (S[i] != T[i]) break
            count++
        }
        sb.appendLine(if (count == 0) (S.length + T.length) else (S.length + T.length - count + 1))
    }
    println(sb)
}