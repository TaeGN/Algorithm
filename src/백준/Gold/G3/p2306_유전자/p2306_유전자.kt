package 백준.Gold.G3.p2306_유전자

fun main() {
    val S = readln()
    val dp = Array(S.length) { IntArray(S.length) }
    for (len in 2..S.length) {
        for (l in 0..(S.length - len)) {
            val r = l + len - 1
            if ((S[l] == 'a' && S[r] == 't') || (S[l] == 'g' && S[r] == 'c')) {
                dp[l][r] = maxOf(dp[l][r], 2 + dp[l + 1][r - 1])
            }
            for (mid in l until r) {
                dp[l][r] = maxOf(dp[l][r], dp[l][mid] + dp[mid + 1][r])
            }
        }
    }
    println(dp.first().last())
}