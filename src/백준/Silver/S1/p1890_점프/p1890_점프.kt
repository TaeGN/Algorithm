package 백준.Silver.S1.p1890_점프

fun main() {
    val N = readln().toInt()
    val matrix = List(N) { readln().split(" ").map(String::toInt) }
    val dp = Array(N) { LongArray(N) }.apply { this[0][0] = 1 }
    for (r in 0 until N) {
        for (c in 0 until N) {
            if (matrix[r][c] == 0) continue
            if (c + matrix[r][c] < N) dp[r][c + matrix[r][c]] += dp[r][c]
            if (r + matrix[r][c] < N) dp[r + matrix[r][c]][c] += dp[r][c]
        }
    }
    println(dp.last().last())
}