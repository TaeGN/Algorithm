package 백준.Gold.G1.p1311_할일정하기1

import java.io.StreamTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val dp = IntArray(1 shl N) { IMPOSSIBLE }
    val priceMatrix = List(N) { IntArray(N) }
    repeat(N) { r ->
        repeat(N) { c ->
            priceMatrix[r][c] = nextInt()
        }
    }

    fun dfs(cur: Int = 0, flag: Int = 0): Int {
        if (flag == (1 shl N) - 1) return 0
        if (dp[flag] != IMPOSSIBLE) return dp[flag]
        var minPrice = IMPOSSIBLE
        for (next in 0 until N) {
            if (flag and (1 shl next) != 0) continue
            minPrice = min(minPrice, dfs(cur + 1, flag or (1 shl next)) + priceMatrix[cur][next])
        }
        return minPrice.also { dp[flag] = it }
    }
    println(dfs())
}