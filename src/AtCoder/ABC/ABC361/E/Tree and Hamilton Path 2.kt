package AtCoder.ABC.ABC361.E

import java.io.StreamTokenizer

const val IMPOSSIBLE = Long.MAX_VALUE shr 2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val roadLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    var totalDist = 0L
    repeat(N - 1) {
        val A = nextInt()
        val B = nextInt()
        val C = nextInt()
        roadLists[A].add(B to C)
        roadLists[B].add(A to C)
        totalDist += C
    }

    val dist = LongArray(N + 1) { IMPOSSIBLE }.apply { this[1] = 0 }
    fun dfs(from: Int) {
        for ((to, weight) in roadLists[from]) {
            if (dist[to] > dist[from] + weight) {
                dist[to] = dist[from] + weight
                dfs(to)
            }
        }
    }

    fun LongArray.maxIdx(): Int {
        var maxValue = 0L
        var maxIdx = 0
        for (idx in 1 until size) {
            if (maxValue < this[idx]) {
                maxIdx = idx
                maxValue = this[idx]
            }
        }
        return maxIdx
    }
    dfs(1)
    val idx1 = dist.maxIdx()
    dist.fill(IMPOSSIBLE)
    dist[idx1] = 0
    dfs(idx1)
    val idx2 = dist.maxIdx()
    println(totalDist * 2 - dist[idx2])
}