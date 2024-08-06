package Codeforces.Div4.Round964.C

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        val s = nextInt()
        val m = nextInt()
        val arr = MutableList(n + 2) { IntArray(2) }.apply { this[n + 1] = intArrayOf(m, m) }
        repeat(n) { idx ->
            arr[idx + 1][0] = nextInt()
            arr[idx + 1][1] = nextInt()
        }
        arr.sortBy { it[0] }
        var isPossible = false
        for (i in 1..n + 1) {
            if (arr[i][0] - arr[i - 1][1] >= s) {
                isPossible = true
                break
            }
        }
        sb.appendLine(if (isPossible) "YES" else "NO")
    }
    println(sb)
}