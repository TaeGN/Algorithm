package 백준.Gold.G4.p5527_전구장식

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val lengthList = mutableListOf<Int>()
    var first = 0
    var second = 0
    var third = 0
    var prev = -1
    repeat(N) {
        val cur = readInt()
        if (prev != cur) {
            third++
        } else {
            lengthList.add(first + second + third)
            first = second
            second = third
            third = 1
        }
        prev = cur
    }
    lengthList.add(first + second + third)
    println(lengthList.max())
}