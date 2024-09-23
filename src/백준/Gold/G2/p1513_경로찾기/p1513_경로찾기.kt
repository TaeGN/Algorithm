package 백준.Gold.G2.p1513_경로찾기

const val MOD = 1_000_007
fun main() {
    val (N, M, C) = readln().trim().split(" ").map { it.toInt() }
    val matrix = Array(N) { IntArray(M) }
    repeat(C) { idx -> readln().trim().split(" ").map { it.toInt() - 1 }.let { matrix[it[0]][it[1]] = idx + 1 } }
    val dp = Array(N) { Array(M) { Array(C + 1) { IntArray(C + 1) } } }
    if (matrix[0][0] > 0) dp[0][0][1][matrix[0][0]] = 1
    else dp[0][0][0][0] = 1
    for (r in 0 until N) {
        for (c in 0 until M) {
            if (matrix[r][c] > 0) {
                val i = matrix[r][c]
                for (k in 0 until i) {
                    for (pi in 0 until i) {
                        if (r > 0) dp[r][c][k + 1][i] = (dp[r][c][k + 1][i] + dp[r - 1][c][k][pi]) % MOD
                        if (c > 0) dp[r][c][k + 1][i] = (dp[r][c][k + 1][i] + dp[r][c - 1][k][pi]) % MOD
                    }
                }
            } else {
                for (k in 0..C) {
                    for (pi in 0..C) {
                        if (r > 0) dp[r][c][k][pi] = (dp[r][c][k][pi] + dp[r - 1][c][k][pi]) % MOD
                        if (c > 0) dp[r][c][k][pi] = (dp[r][c][k][pi] + dp[r][c - 1][k][pi]) % MOD
                    }
                }
            }
        }
    }
    println(dp.last().last().joinToString(" ") { "${it.fold(0) { acc, i -> (acc + i) % MOD }}" })
}