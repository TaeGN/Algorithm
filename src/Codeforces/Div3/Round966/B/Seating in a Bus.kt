package Codeforces.Div3.Round966.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    val set = mutableSetOf<Int>()
    repeat(T) {
        val N = nextInt()
        var isPossible = true
        set.clear()
        repeat(N) {
            val num = nextInt()
            if (isPossible && (set.isEmpty() || num in set)) {
                set.remove(num)
                set.add(num - 1)
                set.add(num + 1)
            } else isPossible = false
        }
        sb.appendLine(if (isPossible) "Yes" else "No")
    }
    println(sb)
}