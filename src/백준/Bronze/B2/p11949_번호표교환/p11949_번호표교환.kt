package 백준.Bronze.B2.p11949_번호표교환

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val numArr = IntArray(n)
    repeat(n) { idx ->
        numArr[idx] = readInt()
    }

    fun IntArray.swap(idx1: Int, idx2: Int) {
        val temp = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = temp
    }

    for (i in 1..m) {
        repeat(n - 1) { j ->
            if (numArr[j] % i > numArr[j + 1] % i) {
                numArr.swap(j, j + 1)
            }
        }
    }

    val sb = StringBuilder()
    numArr.forEach { sb.appendLine(it) }
    println(sb)
}