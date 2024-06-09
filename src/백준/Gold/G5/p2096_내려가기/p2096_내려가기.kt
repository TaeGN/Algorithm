package 백준.Gold.G5.p2096_내려가기

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val maxSumArr = arrayOf(0, 0, 0)
    val minSumArr = arrayOf(0, 0, 0)
    repeat(n) {
        maxSumArr[0] = max(maxSumArr[0], maxSumArr[1])
        maxSumArr[2] = max(maxSumArr[1], maxSumArr[2])
        maxSumArr[1] = max(maxSumArr[0], maxSumArr[2])
        minSumArr[0] = min(minSumArr[0], minSumArr[1])
        minSumArr[2] = min(minSumArr[1], minSumArr[2])
        minSumArr[1] = min(minSumArr[0], minSumArr[2])

        val st = StringTokenizer(readLine())
        val first = st.nextToken().toInt()
        val second = st.nextToken().toInt()
        val third = st.nextToken().toInt()
        maxSumArr[0] += first
        maxSumArr[1] += second
        maxSumArr[2] += third
        minSumArr[0] += first
        minSumArr[1] += second
        minSumArr[2] += third
    }

    val maxSum = max(maxSumArr[0], max(maxSumArr[1], maxSumArr[2]))
    val minSum = min(minSumArr[0], min(minSumArr[1], minSumArr[2]))
    println("$maxSum $minSum")
}