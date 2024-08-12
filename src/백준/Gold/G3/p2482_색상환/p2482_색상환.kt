package 백준.Gold.G3.p2482_색상환

const val MOD = 1_000_000_003
fun main() {
    val N = readln().toInt()
    val K = readln().toInt()
    val dp = Array(N + 1) { IntArray(K + 1).apply { this[0] = 1 } }.apply { this[1][1] = 1 }
    for (n in 2..N) {
        for (k in 1..K) {
            dp[n][k] = (dp[n - 2][k - 1] + dp[n - 1][k]) % MOD
        }
    }
    println((dp[N - 3][K - 1] + dp[N - 1][K]) % MOD)
}