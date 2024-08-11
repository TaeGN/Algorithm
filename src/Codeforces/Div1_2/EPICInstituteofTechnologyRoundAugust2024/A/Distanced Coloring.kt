package Codeforces.Div1_2.EPICInstituteofTechnologyRoundAugust2024.A

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
        val n = nextInt()
        val m = nextInt()
        val k = nextInt()
        sb.appendLine(min(n, k) * min(m, k))
    }
    println(sb)
}