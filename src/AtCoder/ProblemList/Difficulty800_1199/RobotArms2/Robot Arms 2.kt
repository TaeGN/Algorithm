package AtCoder.ProblemList.Difficulty800_1199.RobotArms2

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, x, y) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val temp = BooleanArray(20001)
    val dp = Array(2) { BooleanArray(temp.size) }.apply { this[0][A[0] + 10000] = true; this[1][10000] = true }
    for (i in 1 until N) {
        for (j in temp.indices) {
            if (dp[i % 2][j]) {
                temp[j + A[i]] = true
                temp[j - A[i]] = true
            }
        }
        for (j in temp.indices) {
            dp[i % 2][j] = temp[j]
            temp[j] = false
        }
    }
    println(if (dp[0][x + 10000] && dp[1][y + 10000]) "Yes" else "No")
}