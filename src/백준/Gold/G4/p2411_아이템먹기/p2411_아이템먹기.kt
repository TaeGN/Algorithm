package 백준.Gold.G4.p2411_아이템먹기

const val IMPOSSIBLE = -1
fun main() {
    val (N, M, A, B) = readln().split(" ").map(String::toInt)
    val dp = Array(N + 1) { IntArray(M + 1) }.apply { this[1][1] = 1 }
    val aArr = Array(A) { readln().split(" ").map(String::toInt) }.sortedWith(compareBy({ it[0] }, { it[1] }))
    repeat(B) { readln().split(" ").map(String::toInt).let { dp[it[0]][it[1]] = IMPOSSIBLE } }
    var sr = 1
    var sc = 1
    for ((nr, nc) in aArr + listOf(listOf(N, M))) {
        for (r in sr..nr) {
            for (c in sc..nc) {
                if (dp[r][c] == IMPOSSIBLE) continue
                dp[r][c] = maxOf(dp[r][c], maxOf(0, dp[r - 1][c]) + maxOf(0, dp[r][c - 1]))
            }
        }
        sr = nr
        sc = nc
    }
    println(dp[N][M])
}