package 백준.Platinum.P5.p1146_지그재그서기

const val MOD = 1_000_000
fun main() {
    val N = readln().toInt()
    val dp = Array(N + 1) { Array(N + 1) { IntArray(2) } }
    for (i in 1..N) {
        for (j in 1..N) {
            if (i == j) continue
            val leftCount = if (i < j) j - 2 else j - 1
            val rightCount = if (i > j) N - j - 1 else N - j
            val type = if (i < j) 0 else 1
            dp[leftCount][rightCount][type]++
        }
    }
    for (remain in (N - 3) downTo 0) {
        for (leftCount in 0..remain) {
            val rightCount = remain - leftCount
            for (pLeftCount in (leftCount + 1)..(remain + 1)) {
                val pRightCount = remain + 1 - pLeftCount
                dp[leftCount][rightCount][1] = (dp[leftCount][rightCount][1] + dp[pLeftCount][pRightCount][0]) % MOD
            }
            for (pRightCount in (rightCount + 1)..(remain + 1)) {
                val pLeftCount = remain + 1 - pRightCount
                dp[leftCount][rightCount][0] = (dp[leftCount][rightCount][0] + dp[pLeftCount][pRightCount][1]) % MOD
            }
        }
    }

    println(if (N == 1) 1 else dp[0][0].sum() % MOD)
}