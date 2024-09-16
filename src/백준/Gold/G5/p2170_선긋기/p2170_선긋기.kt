package 백준.Gold.G5.p2170_선긋기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val arr = Array(N) { nextInt() to nextInt() }
    arr.sortBy { it.first }
    var result = 0
    var l = Int.MIN_VALUE
    var r = Int.MIN_VALUE
    for ((nl, nr) in arr) {
        if (nl <= r) {
            r = maxOf(r, nr)
        } else {
            result += r - l
            l = nl
            r = nr
        }
    }
    result += r - l
    println(result)
}