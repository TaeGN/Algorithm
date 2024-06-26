package 백준.Gold.G3.p20303_할로윈의양아치

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun IntArray.find(idx: Int): Int = if (this[idx] == idx) idx else find(this[idx]).also { this[idx] = it }
    fun IntArray.union(idx1: Int, idx2: Int) {
        val root1 = find(idx1)
        val root2 = find(idx2)
        this[root1] = root2
    }

    val n = readInt()
    val m = readInt()
    val k = readInt()
    val candyArr = IntArray(n)
    repeat(n) { idx ->
        candyArr[idx] = readInt()
    }

    val groupArr = IntArray(n) { it }
    repeat(m) {
        val idx1 = readInt() - 1
        val idx2 = readInt() - 1
        groupArr.union(idx1, idx2)
    }

    repeat(n) { idx ->
        groupArr.find(idx)
    }

    val friendAndCandy = (0 until n).groupBy { groupArr[it] }
        .map { it.value.size to it.value.sumOf { idx -> candyArr[idx] } }

    val dp = IntArray(k) { idx -> if (idx == 0) 0 else Int.MIN_VALUE }
    for ((size, candy) in friendAndCandy) {
        for (i in (k - 1) downTo size) {
            if (dp[i - size] == Int.MIN_VALUE) continue
            dp[i] = max(dp[i], dp[i - size] + candy)
        }
    }

    println(dp.max())
}