package 엘리스코드챌린지.떠먹여주는알고리즘.기초수학과문자열.주택청약

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val maxAreaSizeAndMinDepositByCity = listOf(
        85 to listOf(0, 300, 250, 200),
        102 to listOf(0, 600, 400, 300),
        135 to listOf(0, 1000, 700, 400),
        Int.MAX_VALUE to listOf(0, 1500, 1000, 500),
    )

    fun minDepositAmount(areaSize: Int, cityId: Int): Int {
        for ((maxAreaSize, minDepositByCity) in maxAreaSizeAndMinDepositByCity) {
            if (areaSize <= maxAreaSize) return minDepositByCity[cityId]
        }
        throw IllegalArgumentException()
    }


    val N = readInt()
    var count = 0
    repeat(N) {
        if (minDepositAmount(readInt(), readInt()) <= readInt()) count++
    }
    println(count)
}