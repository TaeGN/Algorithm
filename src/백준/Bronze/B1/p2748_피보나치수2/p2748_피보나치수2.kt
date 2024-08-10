package 백준.Bronze.B1.p2748_피보나치수2

fun main() {
    val n = readln().toInt()
    val dp = LongArray(n + 1).apply { this[1] = 1 }
    for (i in 2..n) {
        dp[i] = dp[i - 1] + dp[i - 2]
    }
    println(dp[n])
}