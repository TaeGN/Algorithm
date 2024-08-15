package Codeforces.Div2.EducationalCodeforces.Round169.B

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        var l = nextInt()
        var r = nextInt()
        var L = nextInt()
        var R = nextInt()
        var temp = 0
        if (l > L) {
            temp = L
            L = l
            l = temp
            temp = R
            R = r
            r = temp
        }
        if (r < L) sb.appendLine(1)
        else sb.appendLine(min(r - L, R - L) + (if (l == L) 0 else 1) + (if (r == R) 0 else 1))
    }
    println(sb)
}