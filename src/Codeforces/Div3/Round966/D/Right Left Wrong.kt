package Codeforces.Div3.Round966.D

import java.util.StringTokenizer

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    val sumArr = LongArray(200000)
    repeat(T) {
        val N = readln().toInt()
        val st = StringTokenizer(readln())
        repeat(N) { idx -> sumArr[idx] = sumArr.getOrElse(idx - 1) { 0 } + st.nextToken().toInt() }
        val s = readln()
        var l = -1
        var r = N
        var result = 0L
        while (++l < --r) {
            while (l < r && s[l] != 'L') l++
            while (l < r && s[r] != 'R') r--
            if (l < r) result += sumArr[r] - sumArr.getOrElse(l - 1) { 0 }
        }
        sb.appendLine(result)
    }
    println(sb)
}