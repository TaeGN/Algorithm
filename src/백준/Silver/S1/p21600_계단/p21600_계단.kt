package 백준.Silver.S1.p21600_계단

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    var maxH = 0
    var curH = 0
    repeat(N) {
        val h = readInt()
        if (h > curH) maxH = max(maxH, ++curH)
        else curH = h
    }

    println(maxH)
}