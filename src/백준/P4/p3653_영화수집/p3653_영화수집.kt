package 백준.P4.p3653_영화수집

import java.io.StreamTokenizer

const val MAX_N = 100_000
const val MAX_M = 100_000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val tc = readInt()
    val dvdCount = Array(MAX_N + MAX_M) { 0 }
    val dvdIdx = Array(MAX_N + 1) { 0 }
    val movedDvdIdx = Array(MAX_N) { 0 }
    val sb = StringBuilder()
    repeat(tc) {
        val n = readInt()
        val m = readInt()
        var curIdx = dvdCount.size - n
        var movedIdx = movedDvdIdx.size
        dvdCount.fill(0, dvdCount.size - n - m, dvdCount.size - n + 1)
        dvdCount.fill(1, dvdCount.size - n + 1)
        for (i in 1..n) {
            dvdIdx[i] = dvdCount.size - 1 - n + i
        }
        repeat(m) {
            val num = readInt()
            val idx = dvdIdx[num]
            val outsideCount = movedDvdIdx.size + movedDvdIdx.binarySearch(idx, movedIdx) + 1
            val totalCount = movedDvdIdx.size + movedDvdIdx.binarySearch(curIdx, movedIdx) + 1
            val insideCount = totalCount - outsideCount
            val sum = idx - curIdx - insideCount
            sb.append("$sum ")
            movedDvdIdx[--movedIdx] = idx
            dvdCount[idx]--
            dvdCount[curIdx]++
            dvdIdx[num] = --curIdx
        }

        sb.appendLine()
    }
    println(sb)
}