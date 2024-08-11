package Codeforces.Div1_2.EPICInstituteofTechnologyRoundAugust2024.C

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun dist(x1: Int, y1: Int, x2: Int, y2: Int): Long = (x1 - x2).toLong() * (x1 - x2) + (y1 - y2).toLong() * (y1 - y2)
    val xArr = IntArray(100000)
    val yArr = IntArray(100000)
    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        repeat(n) { idx ->
            xArr[idx] = nextInt()
            yArr[idx] = nextInt()
        }
        val xs = nextInt()
        val ys = nextInt()
        val xt = nextInt()
        val yt = nextInt()
        val dist = dist(xs, ys, xt, yt)
        var isPossible = true
        for (i in 0 until n) {
            if (dist >= dist(xArr[i], yArr[i], xt, yt)) {
                isPossible = false
                break
            }
        }
        if (isPossible) sb.appendLine("Yes")
        else sb.appendLine("No")
    }
    println(sb)
}