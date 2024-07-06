package 백준.Gold.G2.p15317_동방보수

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val X = readInt()
    val nArr = IntArray(N)
    repeat(N) { idx ->
        nArr[idx] = readInt()
    }
    nArr.sort()

    val mArr = IntArray(M)
    repeat(M) { idx ->
        mArr[idx] = readInt()
    }
    mArr.sort()

    fun isPossible(count: Int): Boolean = (0 until count).sumOf { max(0, nArr[it] - mArr[M - count + it]).toLong() } <= X
    fun maxCount(start: Int = 0, end: Int = min(N, M)): Int {
        val mid = (start + end) / 2
        if(start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) maxCount(mid, end)
        else maxCount(start, mid - 1)
    }

    println(maxCount())
}