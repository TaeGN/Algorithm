package 백준.Gold.G3.p1695_팰린드롬만들기

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val arr = readln().trim().split(" ").map(String::toInt)
    fun result(): Int {
        val dp = Array(N) { l -> IntArray(N) { r -> if (l >= r) 0 else IMPOSSIBLE } }
        for (diff in 1 until N) {
            for (l in 0 until (N - diff)) {
                val r = l + diff
                if (arr[l] == arr[r]) dp[l][r] = minOf(dp[l][r], dp[l + 1][r - 1])
                else dp[l][r] = minOf(dp[l][r], dp[l][r - 1] + 1, dp[l + 1][r] + 1)
            }
        }
        return dp.first().last()
    }
    println(result())
}