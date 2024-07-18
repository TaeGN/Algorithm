package 백준.Silver.S3.p28116_선택정렬의이동거리

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    val N = nextInt()
    val moveCountArr = IntArray(N + 1)
    val numArr = IntArray(N + 1)
    val idxArr = IntArray(N + 1)
    for (idx in 1..N) {
        val num = nextInt()
        numArr[idx] = num
        idxArr[num] = idx
    }
    for (num in 1..N) {
        val numA = num
        val idxA = idxArr[numA]
        val idxB = num
        val numB = numArr[idxB]
        moveCountArr[numA] += idxA - idxB
        moveCountArr[numB] += idxA - idxB
        numArr[idxA] = numB
        numArr[idxB] = numA
        idxArr[numA] = idxB
        idxArr[numB] = idxA
        sb.append("${moveCountArr[numA]} ")
    }
    println(sb)
}