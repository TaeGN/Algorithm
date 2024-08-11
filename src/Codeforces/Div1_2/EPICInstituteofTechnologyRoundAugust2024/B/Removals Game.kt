package Codeforces.Div1_2.EPICInstituteofTechnologyRoundAugust2024.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val aArr = IntArray(300000)
    val bArr = IntArray(300000)
    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        repeat(n) { idx -> aArr[idx] = nextInt() }
        repeat(n) { idx -> bArr[idx] = nextInt() }
        var forward = true
        var backward = true
        for (i in 0 until n) {
            if (aArr[i] != bArr[i]) forward = false
            if (aArr[i] != bArr[n - 1 - i]) backward = false
        }
        if (forward || backward) sb.appendLine("Bob")
        else sb.appendLine("Alice")
    }
    println(sb)
}