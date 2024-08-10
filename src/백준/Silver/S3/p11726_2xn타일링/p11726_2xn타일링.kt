package 백준.Silver.S3.p11726_2xn타일링

fun main() {
    val n = readln().toInt()
    val dp = IntArray(n + 1)
    for (i in 1..n) {
        dp[i] = dp.getOrElse(i - 1) { 0 } + dp.getOrElse(i - 2) { 0 }
        if (i in 1..2) dp[i]++
        dp[i] %= 10007
    }
    println(dp[n])
}