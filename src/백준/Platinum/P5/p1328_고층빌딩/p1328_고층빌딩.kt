package 백준.Platinum.P5.p1328_고층빌딩

const val MOD = 1_000_000_007
fun main() {
    val (N, L, R) = readln().split(" ").map(String::toInt)
    val dp = Array(N + 1) { Array(L + 1) { IntArray(R + 1) } }.apply { this[1][1][1] = 1 }
    for (n in 2..N) {
        for (l in 1..L) {
            for (r in 1..R) {
                dp[n][l][r] = (dp[n - 1][l][r].toLong() * (n - 2) % MOD).toInt()
                dp[n][l][r] = (dp[n][l][r] + dp[n - 1][l - 1][r]) % MOD
                dp[n][l][r] = (dp[n][l][r] + dp[n - 1][l][r - 1]) % MOD
            }
        }
    }
    println(dp[N][L][R])
}