package AtCoder.ABC.ABC365.C

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long {
        nextToken()
        return nval.toLong()
    }

    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextLong()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt()
    }
    numArr.sort()
    val sumArr = LongArray(N)
    repeat(N) { idx ->
        sumArr[idx] = sumArr.getOrElse(idx - 1) { 0L } + numArr[idx]
    }

    fun isPossible(x: Int): Boolean {
        val count = numArr.binarySearch(x + 1).let { if (it > 0) it else -it - 1 }
        return -M + sumArr.getOrElse(count - 1) { 0 } + (N - count).toLong() * x <= 0
    }

    fun maxX(start: Int, end: Int): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        if (isPossible(mid)) return maxX(mid, end)
        return maxX(start, mid - 1)
    }

    println(if (sumArr.last() <= M) "infinite" else maxX(0, 1000000000))
}