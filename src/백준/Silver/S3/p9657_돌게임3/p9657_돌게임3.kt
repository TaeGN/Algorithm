package 백준.Silver.S3.p9657_돌게임3

fun main() {
    val N = readln().toInt()
    val dp = BooleanArray(N + 1).apply { this[1] = true }
    for (i in 2..N) {
        dp[i] = !dp[i - 1] || !dp.getOrElse(i - 3) { true } || !dp.getOrElse(i - 4) { true }
    }
    println(if (dp[N]) "SK" else "CY")
}