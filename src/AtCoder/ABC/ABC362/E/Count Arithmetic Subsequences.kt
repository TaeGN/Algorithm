package AtCoder.ABC.ABC362.E

import java.io.StreamTokenizer

const val MOD = 998244353
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val numArr = IntArray(N + 1)
    repeat(N) { idx ->
        numArr[idx + 1] = nextInt()
    }
    val diffMap = mutableMapOf<Int, Int>()
    var diffCount = 0
    for (i in numArr) {
        for (j in numArr) {
            if ((j - i) in diffMap) continue
            diffMap[j - i] = diffCount++
        }
    }
    val dp = List(N + 1) { List(N + 1) { length -> IntArray(diffCount).apply { if (length == 1) this.fill(1) } } }
    val countArr = IntArray(N + 1).apply { this[1] = N }
    for (length in 2..N) {
        for (prevIdx in 1..N) {
            for (nextIdx in (prevIdx + 1)..N) {
                val diff = numArr[nextIdx] - numArr[prevIdx]
                dp[prevIdx][length][diffMap[diff]!!] =
                    (dp[prevIdx][length][diffMap[diff]!!] + dp[nextIdx][length - 1][diffMap[diff]!!]) % MOD
            }
        }
        for (prevIdx in 1..N) {
            for (diff in 0 until diffCount) {
                countArr[length] = (countArr[length] + dp[prevIdx][length][diff]) % MOD
            }
        }
    }

    val sb = StringBuilder()
    for (i in 1..N) {
        sb.append("${countArr[i]} ")
    }
    println(sb)
}