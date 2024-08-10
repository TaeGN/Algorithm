package 백준.Silver.S3.p11727_2xn타일링2

fun main() {
    val n = readln().toInt()
    val dp = IntArray(n + 1)
    for (i in 1..n) {
        if (i == 1) dp[i] = 1
        else if (i == 2) dp[i] = 2
        dp[i] = (dp[i] + dp[i - 1] + dp.getOrElse(i - 2) { 0 } * 2) % 10007
    }
    println(dp.last())
}