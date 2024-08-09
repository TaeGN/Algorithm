package 백준.Gold.G1.p2098_외판원순회

import java.io.StreamTokenizer
import kotlin.math.min

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val EMPTY = -1
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val matrix = List(N) { IntArray(N) }
    repeat(N) { r ->
        repeat(N) { c ->
            matrix[r][c] = nextInt().let { if (it == 0) IMPOSSIBLE else it }
        }
    }

    val dp = List(N) { IntArray(1 shl N) { EMPTY } }
    fun dfs(cur: Int = 0, flag: Int = 1): Int {
        if (dp[cur][flag] != EMPTY) return dp[cur][flag]
        if (flag == (1 shl N) - 1) return matrix[cur][0].also { dp[cur][flag] = it }
        var minPrice = IMPOSSIBLE
        for (next in 0 until N) {
            if (flag and (1 shl next) != 0 || matrix[cur][next] == IMPOSSIBLE) continue
            minPrice = min(minPrice, matrix[cur][next] + dfs(next, flag or (1 shl next)))
        }
        return minPrice.also { dp[cur][flag] = it }
    }

    println(dfs())
}
