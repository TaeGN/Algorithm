package 백준.Gold.G4.p16169_수행시간

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val computerLists = List(N + 2) { mutableListOf<Pair<Int, Int>>() }
    val dp = IntArray(N + 1)
    repeat(N) { idx ->
        computerLists[nextInt()].add((idx + 1) to nextInt())
    }
    computerLists[1].forEach { dp[it.first] = it.second }
    for (rank in 1..N) {
        for ((idx, _) in computerLists[rank]) {
            for ((nextIdx, nextT) in computerLists[rank + 1]) {
                dp[nextIdx] = max(dp[nextIdx], dp[idx] + (idx - nextIdx) * (idx - nextIdx) + nextT)
            }
        }
    }
    println(dp.max())
}