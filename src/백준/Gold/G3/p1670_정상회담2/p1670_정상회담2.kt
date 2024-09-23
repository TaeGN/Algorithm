package 백준.Gold.G3.p1670_정상회담2

const val MOD = 987654321
fun main() {
    val N = readln().toInt() / 2
    val dp = LongArray(N + 1).apply { this[0] = 1 }
    for (i in 0 until N) {
        for (j in 0..i) {
            dp[i + 1] = (dp[i + 1] + dp[j] * dp[i - j]) % MOD
        }
    }
    println(dp[N])
}