package 백준.Gold.G1.p1176_섞기

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val K = nextInt()
    val dp = List(1 shl N) { LongArray(N) }
    for (id in 0 until N) {
        dp[1 shl id][id] = 1
    }

    val hArr = IntArray(N)
    repeat(N) { idx ->
        hArr[idx] = nextInt()
    }

    for (curFlag in 1 until dp.size - 1) {
        for (nextId in 0 until N) {
            if ((curFlag and (1 shl nextId)) != 0) continue
            val nextFlag = curFlag or (1 shl nextId)
            for (curId in 0 until N) {
                if (abs(hArr[nextId] - hArr[curId]) > K) {
                    dp[nextFlag][nextId] += dp[curFlag][curId]
                }
            }
        }
    }

    println(dp.last().sum())
}