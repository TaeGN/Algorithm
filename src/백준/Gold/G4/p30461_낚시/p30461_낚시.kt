package 백준.Gold.G4.p30461_낚시

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val Q = nextInt()
    val map = List(N) { IntArray(M) }
    fun List<IntArray>.get(r: Int, c: Int): Int = if (r in indices && c in this[0].indices) this[r][c] else 0
    repeat(N) { r ->
        repeat(M) { c ->
            map[r][c] = nextInt() + map.get(r - 1, c) + map.get(r - 1, c - 1) - map.get(r - 2, c - 1)
        }
    }
    val sb = StringBuilder()
    repeat(Q) {
        sb.appendLine(map.get(nextInt() - 1, nextInt() - 1))
    }
    println(sb)
}