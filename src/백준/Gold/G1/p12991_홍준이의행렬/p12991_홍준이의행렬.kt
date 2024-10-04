package 백준.Gold.G1.p12991_홍준이의행렬

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val K = nextInt()
    val A = IntArray(N) { nextInt() }
    val B = IntArray(N) { nextInt() }
    A.sort()
    B.sort()
    fun IntArray.upperBound(target: Int, start: Int = 0, end: Int = size - 1): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (this[start] > target) start else if (this[end] > target) end else size
        return if (this[mid] > target) upperBound(target, start, mid)
        else upperBound(target, mid + 1, end)
    }

    fun isPossible(mul: Long): Boolean {
        var result = 0
        for (a in A) {
            result += B.upperBound(minOf(Int.MAX_VALUE.toLong(), mul / a).toInt())
        }
        return result >= K
    }

    fun search(start: Long = 1, end: Long = 1_000_000_000_000_000_000): Long {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(start)) start else end
        return if (isPossible(mid)) search(start, mid)
        else search(mid + 1, end)
    }

    println(search())
}