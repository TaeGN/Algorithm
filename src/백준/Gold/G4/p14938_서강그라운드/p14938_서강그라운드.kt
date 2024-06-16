package 백준.Gold.G4.p14938_서강그라운드

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val r = readInt()
    val itemCount = IntArray(n + 1)
    repeat(n) { idx ->
        itemCount[idx + 1] = readInt()
    }

    val roadMatrix = List(n + 1) { from -> IntArray(n + 1) { to -> if (from == to) 0 else Int.MAX_VALUE } }
    fun List<IntArray>.isNotValid(start: Int, end: Int): Boolean = start == end || this[start][end] == Int.MAX_VALUE
    repeat(r) {
        val areaA = readInt()
        val areaB = readInt()
        val weight = readInt()
        roadMatrix[areaA][areaB] = weight
        roadMatrix[areaB][areaA] = weight
    }

    for (mid in 1..n) {
        for (start in 1..n) {
            if (roadMatrix.isNotValid(start, mid)) continue
            for (end in 1..n) {
                if (roadMatrix.isNotValid(mid, end)) continue
                roadMatrix[start][end] = min(roadMatrix[start][end], roadMatrix[start][mid] + roadMatrix[mid][end])
            }
        }
    }

    roadMatrix.maxOf { roadList ->
        roadList.foldIndexed(0) { index, acc, weight -> acc + (if (weight <= m) itemCount[index] else 0) }
    }.let(::println)
}