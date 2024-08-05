package AtCoder.ABC.ABC365.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    var first = 0
    var second = 0
    var firstIdx = 0
    var secondIdx = 0
    repeat(N) { idx ->
        val num = nextInt()
        if (first < num) {
            second = first
            secondIdx = firstIdx
            first = num
            firstIdx = idx
        } else if (second < num) {
            second = num
            secondIdx = idx
        }
    }
    println(secondIdx + 1)
}