package Codeforces.Div4.Round964.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun score(a: Int, b: Int): Int = a.compareTo(b)

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val a1 = nextInt()
        val a2 = nextInt()
        val b1 = nextInt()
        val b2 = nextInt()
        var count = 0
        if((score(a1,b1) + score(a2,b2)) > 0) count += 2
        if((score(a1,b2) + score(a2,b1)) > 0) count += 2
        sb.appendLine(count)
    }
    println(sb)
}