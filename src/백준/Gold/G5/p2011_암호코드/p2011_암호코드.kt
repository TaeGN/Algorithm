package 백준.Gold.G5.p2011_암호코드

fun main() {
    val numArr = readln().map(Char::digitToInt)
    val dp = intArrayOf(0, 0, 1)
    for (idx in numArr.indices) {
        dp[0] = dp[1]
        dp[1] = dp[2]
        dp[2] = ((if (numArr[idx] > 0) dp[1] else 0) +
                (if ((numArr.getOrElse(idx - 1) { 0 } * 10 + numArr[idx]) in 10..26) dp[0] else 0)) % 1000000
        if (dp[2] == 0) break
    }
    println(dp[2])
}