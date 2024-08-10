package 백준.Silver.S3.p2193_이친수

fun main() {
    val N = readln().toInt()
    val dp = LongArray(2).apply { this[1] = 1 }
    for (i in 2..N) {
        val zero = dp[0]
        dp[0] = zero + dp[1]
        dp[1] = zero
    }
    println(dp.sum())
}