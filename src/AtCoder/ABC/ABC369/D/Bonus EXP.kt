package AtCoder.ABC.ABC369.D


fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toLong)
    val dp = longArrayOf(0, Long.MIN_VALUE)
    for (A in aArr) {
        val n1 = maxOf(dp[0], dp[1] + 2 * A)
        val n2 = maxOf(dp[1], dp[0] + A)
        dp[0] = n1
        dp[1] = n2
    }
    println(dp.max())
}