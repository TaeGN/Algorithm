package 백준.Gold.G3.p9025_WidestPath

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.min

const val MAX_N = 1000
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val dp = IntArray(MAX_N + 1)
    val matrix = Array(MAX_N + 1) { IntArray(MAX_N + 1) }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { -it.second })
    val t = nextInt()
    repeat(t) {
        val N = nextInt()
        val M = nextInt()
        val S = nextInt()
        val T = nextInt()
        pq.clear()
        dp.fill(0, 1, N + 1)
        for (i in 1..N) {
            for (j in 1..N) {
                matrix[i][j] = IMPOSSIBLE
            }
        }
        repeat(M) {
            val u = nextInt()
            val v = nextInt()
            val b = nextInt()
            matrix[u][v] = b
            matrix[v][u] = b
        }
        var result = 0
        pq.add(S to IMPOSSIBLE)
        while (pq.isNotEmpty()) {
            val (from, weight) = pq.poll()
            if (from == T) {
                result = weight
                break
            }
            if (weight < dp[from]) continue
            for (to in 1..N) {
                val nextWeight = min(weight, matrix[from][to])
                if (matrix[from][to] == IMPOSSIBLE || dp[to] >= nextWeight) continue
                dp[to] = nextWeight
                pq.add(to to dp[to])
            }
        }
        sb.appendLine(result)
    }
    println(sb)
}