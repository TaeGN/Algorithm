package Codeforces.Div4.Round964.G1

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun run(start: Int = 0, end: Int = 1000) {
        if (start >= end) {
            println("! $end")
            return
        }
        val mid = (start + end) / 2
        println("? $mid $end")
        val mul = nextInt()
        if (mul == (mid + 1) * (end + 1)) run(start, mid)
        else run(mid + 1, end)
    }

    val T = nextInt()
    repeat(T) {
        run()
    }
    System.out.flush()
}