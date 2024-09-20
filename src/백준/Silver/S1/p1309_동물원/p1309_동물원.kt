package 백준.Silver.S1.p1309_동물원

const val MOD = 9901
fun main() {
    val N = readln().toInt()
    val dp = IntArray(3) { 1 }
    repeat(N - 1) {
        val n0 = dp.sum() % MOD
        val n1 = (dp[0] + dp[2]) % MOD
        val n2 = (dp[0] + dp[1]) % MOD
        dp[0] = n0
        dp[1] = n1
        dp[2] = n2
    }
    println(dp.sum() % MOD)
}