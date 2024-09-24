package 백준.Gold.G3.p2216_문자열과정수

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (A, B, C) = readln().trim().split(" ").map(String::toInt)
    val X = " " + readln().trim()
    val Y = " " + readln().trim()
    val dp = Array(X.length) { IntArray(Y.length) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
    for (i in X.indices) {
        for (j in Y.indices) {
            if (i > 0) dp[i][j] = maxOf(dp[i][j], dp[i - 1][j] + B)
            if (j > 0) dp[i][j] = maxOf(dp[i][j], dp[i][j - 1] + B)
            if (i > 0 && j > 0) dp[i][j] = maxOf(dp[i][j], dp[i - 1][j - 1] + if (X[i] == Y[j]) A else C)
        }
    }
    println(dp.last().last())
}