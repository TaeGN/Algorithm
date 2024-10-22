package AtCoder.ProblemList.Difficulty800_1199.StringBags

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val T = readln().trim()
    val N = readln().trim().toInt()
    val matrix = Array(N) { readln().trim().split(" ") }
    val dp = Array(N + 1) { IntArray(T.length + 1) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
    for (i in 1..N) {
        for (j in dp[i].indices) {
            dp[i][j] = dp[i - 1][j]
        }
        for (j in 1 until matrix[i - 1].size) {
            val str = matrix[i - 1][j]
            for (k in 0..(T.length - str.length)) {
                if (dp[i][k] == IMPOSSIBLE) continue
                var isPossible = true
                for (l in str.indices) {
                    if (str[l] != T[k + l]) {
                        isPossible = false
                        break
                    }
                }
                if (isPossible) dp[i][k + str.length] = minOf(dp[i][k + str.length], dp[i - 1][k] + 1)
            }
        }
    }
    println(dp.last().last().let { if (it == IMPOSSIBLE) -1 else it })
}