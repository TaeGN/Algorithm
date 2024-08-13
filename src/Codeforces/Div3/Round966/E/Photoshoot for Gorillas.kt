package Codeforces.Div3.Round966.E

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val T = nextInt()
    val wArr = IntArray(200000)
    val pq = PriorityQueue<Pair<Long, Int>>(compareBy { -it.first })
    repeat(T) {
        val N = nextInt()
        val M = nextInt()
        val K = nextInt()
        val W = nextInt()
        repeat(W) { idx -> wArr[idx] = nextInt() }
        wArr.sortDescending(0, W)
        val rK = if (N % 2 == 0) 2 else 1
        val cK = if (M % 2 == 0) 2 else 1
        val rMax = (N + 1) / 2
        val cMax = (M + 1) / 2
        val rkMax = min(K, N - K + 1)
        val ckMax = min(K, M - K + 1)
        var wIdx = 0
        var result = 0L
        for (r in rMax downTo 0) {
            for (c in cMax downTo 0) {
                val weight = min(rkMax, r).toLong() * min(ckMax, c)
                val count = (if (r == rMax) rK else 2) * (if (c == cMax) cK else 2)
                pq.add(weight to count)
            }
        }
        while (wIdx < W) {
            val (weight, count) = pq.poll()
            repeat(count) { if (wIdx < W) result += wArr[wIdx++].toLong() * weight }
        }
        sb.appendLine(result)
        pq.clear()
    }
    println(sb)
}