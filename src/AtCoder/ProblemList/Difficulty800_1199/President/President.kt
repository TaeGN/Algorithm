package AtCoder.ProblemList.Difficulty800_1199.President

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val N = readln().trim().toInt()
    val arr = Array(N) { readln().trim().split(" ").map(String::toInt) }
    val D = Array(N) { i -> intArrayOf((arr[i][1] - arr[i][0]).let { if (it > 0) (it + 1) / 2 else 0 }, arr[i][2]) }
    val totalZ = arr.sumOf { it[2] }
    val dp = LongArray(100001) { IMPOSSIBLE }.apply { this[0] = 0 }
    for ((weight, z) in D) {
        for (i in (dp.size - 1) downTo z) {
            dp[i] = minOf(dp[i], dp[i - z] + weight)
        }
    }
    var result = IMPOSSIBLE
    for (i in (totalZ / 2 + 1) until dp.size) {
        result = minOf(result, dp[i])
    }
    println(result)
}