package 백준.Gold.G5.p14671_영정이의청소

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val K = readInt()
    val moldArr = List(2) { BooleanArray(2) }
    repeat(K) {
        moldArr[readInt() % 2][readInt() % 2] = true
    }
    println(if (moldArr.all { row -> row.all { it } }) "YES" else "NO")
}