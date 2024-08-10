package 백준.Silver.S1.p10844_쉬운계단수

const val MOD = 1_000_000_000
fun main() {
    val N = readln().toInt()
    val dp = List(N + 1) { IntArray(10) }.apply { this[1].fill(1); this[1][0] = 0 }
    for (i in 2..N) {
        for (j in 0 until 10) {
            dp[i][j] = ((dp[i][j] + dp[i - 1].getOrElse(j - 1) { 0 }) % MOD + dp[i - 1].getOrElse(j + 1) { 0 }) % MOD
        }
    }
    println(dp.last().fold(0) { acc, i -> (acc + i) % MOD })
}