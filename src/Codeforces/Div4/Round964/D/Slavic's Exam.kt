package Codeforces.Div4.Round964.D

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val T = readLine().toInt()
    repeat(T) {
        val s = readLine().toCharArray()
        val t = readLine()
        var tIdx = 0
        for ((sIdx, c) in s.withIndex()) {
            if (c == '?' || c == t[tIdx]) {
                s[sIdx] = t[tIdx]
                if (++tIdx == t.length) break
            }
        }
        if (tIdx == t.length) sb.appendLine("YES").appendLine(s.joinToString("").replace('?', 'a'))
        else sb.appendLine("NO")
    }
    println(sb)
}