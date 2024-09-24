package 백준.Gold.G3.p4175_PickUpSticks

import java.io.StreamTokenizer

const val MAX_N = 1_000_000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val outLists = List(MAX_N + 1) { mutableListOf<Int>() }
    val inDegree = IntArray(MAX_N + 1)
    val result = IntArray(MAX_N)
    val queue = ArrayDeque<Int>()
    val sb = StringBuilder()
    while (true) {
        val N = nextInt()
        val M = nextInt()
        if (N == 0) break
        for (i in 1..N) {
            outLists[i].clear()
            inDegree[i] = 0
        }
        queue.clear()
        repeat(M) {
            val A = nextInt()
            val B = nextInt()
            outLists[A].add(B)
            inDegree[B]++
        }
        for (i in 1..N) {
            if (inDegree[i] == 0) queue.add(i)
        }
        var count = 0
        while (queue.isNotEmpty()) {
            val from = queue.removeFirst()
            result[count++] = from
            for (to in outLists[from]) {
                if (--inDegree[to] == 0) queue.add(to)
            }
        }
        if (count == N) {
            for (i in 0 until N) {
                sb.appendLine(result[i])
            }
        } else sb.appendLine("IMPOSSIBLE")
    }
    println(sb)
}