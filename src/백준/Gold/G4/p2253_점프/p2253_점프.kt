package 백준.Gold.G4.p2253_점프

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val isNotValid = BooleanArray(N + 1)
    repeat(M) { isNotValid[readln().toInt()] = true }
    val dp = Array(N + 1) { IntArray(141) { IMPOSSIBLE } }.apply { this[1][0] = 0 }
    for (i in 2..N) {
        if (isNotValid[i]) continue
        for (j in 1..minOf(140, i)) {
            dp[i][j] = minOf(dp[i][j], dp[i - j][j - 1] + 1)
            dp[i][j] = minOf(dp[i][j], dp[i - j][j] + 1)
            if (j < 140) dp[i][j] = minOf(dp[i][j], dp[i - j][j + 1] + 1)
        }
    }
    println(dp[N].min().let { if (it == IMPOSSIBLE) -1 else it })
}