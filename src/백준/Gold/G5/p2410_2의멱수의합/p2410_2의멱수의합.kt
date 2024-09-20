package 백준.Gold.G5.p2410_2의멱수의합

const val MOD = 1_000_000_000
fun main() {
    val N = readln().toInt()
    val dp = IntArray(N + 1).apply { this[0] = 1 }
    for (i in 1..N) {
        if(i % 2 == 1) dp[i] = dp[i - 1]
        else dp[i] = (dp[i - 1] + dp[i / 2]) % MOD
    }
    println(dp[N])
}