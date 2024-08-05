package AtCoder.ABC.ABC364.D

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val Q = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt()
    }
    numArr.sort()

    fun IntArray.lowerBound(target: Int, start: Int = 0, end: Int = size): Int {
        if (start >= end) return end
        val mid = (start + end) / 2
        return if (this[mid] >= target) lowerBound(target, start, mid)
        else lowerBound(target, mid + 1, end)
    }

    fun IntArray.upperBound(target: Int, start: Int = 0, end: Int = size): Int {
        if (start >= end) return end
        val mid = (start + end) / 2
        return if (this[mid] <= target) upperBound(target, mid + 1, end)
        else upperBound(target, start, mid)
    }

    fun isPossible(x: Int, b: Int, k: Int): Boolean {
        val fromIndex = numArr.lowerBound(b - x)
        val toIndex = numArr.upperBound(b + x)
        return toIndex - fromIndex >= k
    }

    fun distance(start: Int = 0, end: Int = 200000000, b: Int, k: Int): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(start, b, k)) start else end
        return if (isPossible(mid, b, k)) distance(start, mid, b, k)
        else distance(mid + 1, end, b, k)
    }

    val sb = StringBuilder()
    repeat(Q) {
        val b = nextInt()
        val k = nextInt()
        sb.appendLine(distance(b = b, k = k))
    }
    println(sb)
}