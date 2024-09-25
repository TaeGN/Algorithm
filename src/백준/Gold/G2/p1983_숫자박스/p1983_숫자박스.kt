package 백준.Gold.G2.p1983_숫자박스

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val top = readln().trim().split(" ").map(String::toInt).filter { it != 0 }
    val bottom = readln().trim().split(" ").map(String::toInt).filter { it != 0 }
    val topEmptySize = N - top.size
    val bottomEmptySize = N - bottom.size
    val dp = Array(N) { Array(topEmptySize + 1) { IntArray(bottomEmptySize + 1) { IMPOSSIBLE } } }
    if (top.isNotEmpty() && bottom.isNotEmpty()) dp[0][0][0] = top[0] * bottom[0]
    if (topEmptySize > 0) dp[0][1][0] = 0
    if (bottomEmptySize > 0) dp[0][0][1] = 0
    if (topEmptySize > 0 && bottomEmptySize > 0) dp[0][1][1] = 0
    for (i in 1 until N) {
        for (topEmpty in 0..topEmptySize) {
            for (bottomEmpty in 0..bottomEmptySize) {
                val topIdx = i - topEmpty
                val bottomIdx = i - bottomEmpty
                if (topIdx in top.indices && bottomIdx in bottom.indices) {
                    dp[i][topEmpty][bottomEmpty] = maxOf(
                        dp[i][topEmpty][bottomEmpty],
                        top[topIdx] * bottom[bottomIdx] + dp[i - 1][topEmpty][bottomEmpty]
                    )
                }
                if (topEmpty > 0 && bottomIdx in bottom.indices) {
                    dp[i][topEmpty][bottomEmpty] = maxOf(
                        dp[i][topEmpty][bottomEmpty],
                        dp[i - 1][topEmpty - 1][bottomEmpty]
                    )
                }
                if (bottomEmpty > 0 && topIdx in top.indices) {
                    dp[i][topEmpty][bottomEmpty] = maxOf(
                        dp[i][topEmpty][bottomEmpty],
                        dp[i - 1][topEmpty][bottomEmpty - 1]
                    )
                }
                if (topEmpty > 0 && bottomEmpty > 0) {
                    dp[i][topEmpty][bottomEmpty] = maxOf(
                        dp[i][topEmpty][bottomEmpty],
                        dp[i - 1][topEmpty - 1][bottomEmpty - 1]
                    )
                }
            }
        }
    }
    println(dp.last().last().last())
}