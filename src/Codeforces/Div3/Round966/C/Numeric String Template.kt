package Codeforces.Div3.Round966.C

import java.util.StringTokenizer


fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    val alphabetArr = BooleanArray(26)
    repeat(T) {
        val N = readln().toInt()
        val st = StringTokenizer(readln())
        val map = mutableMapOf<Int, MutableList<Int>>()
        repeat(N) { idx ->
            map.compute(
                st.nextToken().toInt()
            ) { _, v -> v?.also { it.add(idx) } ?: mutableListOf(idx) }
        }
        val M = readln().toInt()
        repeat(M) {
            val S = readln()
            var isPossible = S.length == N
            alphabetArr.fill(false)
            if (isPossible) {
                a@ for (list in map.values) {
                    val c = S[list[0]]
                    if (alphabetArr[c - 'a']) {
                        isPossible = false
                        break@a
                    }
                    alphabetArr[c - 'a'] = true
                    for (idx in list) {
                        if (idx < S.length && c != S[idx]) {
                            isPossible = false
                            break@a
                        }
                    }
                }
            }
            sb.appendLine(if (isPossible) "Yes" else "No")
        }
    }
    println(sb)
}