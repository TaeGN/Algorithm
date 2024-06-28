package 백준.Silver.S3.p11441_합구하기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val sumArr = IntArray(N + 1)
    repeat(N) { idx ->
        sumArr[idx + 1] = sumArr.getOrElse(idx) { 0 } + readInt()
    }

    val sb = StringBuilder()
    val M = readInt()
    repeat(M) {
        val startIdx = readInt()
        val endIdx = readInt()
        sb.appendLine(sumArr[endIdx] - sumArr[startIdx - 1])
    }

    println(sb)
}