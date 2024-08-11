package 백준.Silver.S1.p11057_오르막수

const val MOD = 10007
fun main() {
    val N = readln().toInt()
    val dp = List(N) { IntArray(10) }.apply { this[0].fill(1) }
    for (i in 1 until N) {
        for (j in 0 until 10) {
            for (k in 0..j) {
                dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD
            }
        }
    }
    println(dp.last().reduce { acc, i -> (acc + i) % MOD })
}