package 백준.Silver.S4.p30404_오리와박수치는춘배

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val K = readInt()
    var count = 0
    var lastClapTime = 0
    repeat(N) {
        val time = readInt()
        if(lastClapTime !in time..(time + K)) {
            lastClapTime = time + K
            count++
        }
    }

    println(count)
}