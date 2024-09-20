package 백준.Gold.G3.p2228_구간나누기

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val nArr = Array(N) { readln().toInt() }
    val dp = Array(N + 1) { IntArray(M + 1) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
    nArr.forEachIndexed { index, i -> dp[index + 1][1] = i }
    for (n in 1..N) {
        for (m in 1..M) {
            dp[n][m] = maxOf(dp[n][m], dp[n - 1][m] + nArr[n - 1])
            for (pn in 0 until n - 1) {
                dp[n][m] = maxOf(dp[n][m], dp[pn][m - 1] + nArr[n - 1])
            }
        }
    }
    println(dp.maxOf { it[M] })
}