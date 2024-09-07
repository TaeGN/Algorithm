package AtCoder.AHC.AHC036

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val T = nextInt()
    val La = nextInt()
    val Lb = nextInt()
    val roadMatrix = Array(N) { Array(N) { "#" } }
    repeat(M) {
        val u = nextInt()
        val v = nextInt()
        roadMatrix[u][v] = "$v "
        roadMatrix[v][u] = "$u "
    }
    for (k in 0 until N) {
        for (i in 0 until N) {
            if (i == k || roadMatrix[i][k] == "#") continue
            for (j in 0 until N) {
                if (roadMatrix[k][j] == "#") continue
                if (roadMatrix[i][j] == "#" || roadMatrix[i][j].length > roadMatrix[i][k].length + roadMatrix[k][j].length) {
                    roadMatrix[i][j] = roadMatrix[i][k] + roadMatrix[k][j]
                }
            }
        }
    }

    val tArr = IntArray(T) { nextInt() }
    var prevIdx = 0
    var sequence = ""
    for (nextIdx in tArr) {
        sequence += roadMatrix[prevIdx][nextIdx]
        prevIdx = nextIdx
    }
    val sequenceArr = sequence.trim().split(" ").map(String::toInt).toIntArray()
    val sb = StringBuilder()
    val aArr = IntArray(La)
    val posMap = mutableMapOf<Int, Int>()
    val visited = BooleanArray(N)
    var aIdx = 0
    for (idx in sequenceArr) {
        if (!visited[idx]) {
            posMap[idx] = aIdx
            visited[idx] = true
            aArr[aIdx++] = idx
        }
    }
    sb.appendLine(aArr.joinToString(" "))
    var sIdx = 0
    while (sIdx < sequenceArr.size) {
        val startSIdx = sIdx
        var minIdx = posMap[sequenceArr[sIdx]]!!
        var maxIdx = minIdx
        while (++sIdx < sequenceArr.size) {
            val nextIdx = posMap[sequenceArr[sIdx]]!!
            val nextMinIdx = minOf(minIdx, nextIdx)
            val nextMaxIdx = maxOf(maxIdx, nextIdx)
            if (nextMaxIdx - nextMinIdx >= Lb) break
            minIdx = nextMinIdx
            maxIdx = nextMaxIdx
        }
        sb.appendLine("s $Lb ${minOf(La - Lb, minIdx)} 0")
        for (i in startSIdx until sIdx) {
            sb.appendLine("m ${sequenceArr[i]}")
        }
    }
    println(sb)
}