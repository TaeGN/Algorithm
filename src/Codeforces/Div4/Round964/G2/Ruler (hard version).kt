package Codeforces.Div4.Round964.G2

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val T = nextInt()
    repeat(T) {
        var start = 1
        var end = 999
        while (end - start > 2) {
            val first = (2 * start + end) / 3
            val second = (start + 2 * end) / 3
            println("? $first $second")
            when (nextInt()) {
                first * second -> start = second
                (first + 1) * (second + 1) -> end = first
                else -> {
                    start = first
                    end = second
                }

            }
        }
        if (end - start == 2) {
            println("? $start ${start + 1}")
            end = when (nextInt()) {
                start * (start + 1) -> start + 2
                start * (start + 2) -> start + 1
                else -> start
            }
        }
        println("! $end")
    }
    System.out.flush()
}