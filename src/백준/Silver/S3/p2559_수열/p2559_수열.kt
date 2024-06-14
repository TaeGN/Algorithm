package 백준.Silver.S3.p2559_수열

import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val numList = br.readLine().split(" ").asSequence().map(String::toInt).toMutableList()
    var sum = numList.asSequence().take(k).sum()
    var maxSum = sum
    repeat(n - k) { idx ->
        sum = sum - numList[idx] + numList[idx + k]
        maxSum = max(maxSum, sum)
    }

    println(maxSum)
}