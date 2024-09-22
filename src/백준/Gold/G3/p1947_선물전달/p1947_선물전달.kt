package 백준.Gold.G3.p1947_선물전달

const val MOD = 1_000_000_000
fun main() {
    val N = readln().toInt()
    val dp = longArrayOf(0, 0, 1)
    for (i in 1..N) {
        dp[0] = dp[1]
        dp[1] = dp[2]
        dp[2] = (i - 1) * (dp[0] + dp[1]) % MOD
    }
    println(dp.last())
}