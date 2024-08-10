package 백준.Silver.S2.p15988_123더하기3

const val MAX_N = 1_000_000
const val MOD = 1_000_000_009
fun main() {
    val set = setOf(1, 2, 3)
    val dp = IntArray(MAX_N + 1)
    for (i in 1..MAX_N) {
        if (i in set) dp[i] = 1
        dp[i] = (dp[i] + dp[i - 1]) % MOD
        dp[i] = (dp[i] + dp.getOrElse(i - 2) { 0 }) % MOD
        dp[i] = (dp[i] + dp.getOrElse(i - 3) { 0 }) % MOD
    }
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        sb.appendLine(dp[readln().toInt()])
    }
    println(sb)
}