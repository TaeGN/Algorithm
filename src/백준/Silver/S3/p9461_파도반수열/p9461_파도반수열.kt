package 백준.Silver.S3.p9461_파도반수열

const val MAX_N = 100
fun main() {
    val dp = LongArray(MAX_N + 1).apply { this[1] = 1 }
    for (i in 2..MAX_N) {
        dp[i] = dp[i - 2] + dp.getOrElse(i - 3) { 1 }
    }
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        sb.appendLine(dp[readln().toInt()])
    }
    println(sb)
}