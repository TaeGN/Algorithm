package AtCoder.ABC.ABC375.E

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val AB = Array(N) { readln().trim().split(" ").map(String::toInt) }
    fun result(): Int {
        val sumB = AB.sumOf { it[1] }
        if (sumB % 3 != 0) return -1
        val dp = Array(N) { Array(sumB + 1) { IntArray(sumB + 1) { IMPOSSIBLE } } }
        dp[0][AB[0][1]][0] = if (AB[0][0] == 1) 0 else 1
        dp[0][0][AB[0][1]] = if (AB[0][0] == 2) 0 else 1
        dp[0][0][0] = if (AB[0][0] == 3) 0 else 1
        for (i in 1 until N) {
            for (j in 0..sumB) {
                for (k in 0..sumB) {
                    if (j >= AB[i][1]) {
                        dp[i][j][k] = minOf(dp[i][j][k], dp[i - 1][j - AB[i][1]][k] + (if (AB[i][0] == 1) 0 else 1))
                    }
                    if (k >= AB[i][1]) {
                        dp[i][j][k] = minOf(dp[i][j][k], dp[i - 1][j][k - AB[i][1]] + (if (AB[i][0] == 2) 0 else 1))
                    }
                    dp[i][j][k] = minOf(dp[i][j][k], dp[i - 1][j][k] + (if (AB[i][0] == 3) 0 else 1))
                }
            }
        }
        return dp.last()[sumB / 3][sumB / 3].let { if (it == IMPOSSIBLE) -1 else it }
    }
    println(result())
}