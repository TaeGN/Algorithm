package 백준.Silver.S2.p11053_가장긴증가하는부분수열

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val lis = mutableListOf<Int>()
    repeat(n) {
        val num = readInt()
        val bsIdx = lis.binarySearch(num)
        if (bsIdx < 0) {
            val idx = -(bsIdx + 1)
            if (idx == lis.size) {
                lis.add(num)
            } else {
                lis[idx] = num
            }
        }
    }

    println(lis.size)
}