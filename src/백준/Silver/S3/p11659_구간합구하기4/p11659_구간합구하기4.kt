package 백준.Silver.S3.p11659_구간합구하기4

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt() + numArr.getOrElse(idx - 1) { 0 }
    }
    val sb = StringBuilder()
    repeat(M) {
        sb.appendLine(-numArr.getOrElse(nextInt() - 2) { 0 } + numArr[nextInt() - 1])
    }
    println(sb)
}