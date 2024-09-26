package 백준.Platinum.P5.p1413_박스안의열쇠

const val EMPTY = -1L
fun main() {
    fun gcd(a: Long, b: Long): Long = if (minOf(a, b) == 0L) maxOf(a, b)
    else gcd(minOf(a, b), maxOf(a, b) % minOf(a, b))
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val dp = Array(N + 1) { n -> LongArray(N + 1) { m -> if (n == 0) 1 else if (m == 0) 0 else EMPTY } }
    fun dp(n: Int, m: Int): Long {
        if (dp[n][m] == EMPTY) dp[n][m] = dp(n - 1, m - 1) + (n - 1) * dp(n - 1, m)
        return dp[n][m]
    }
    val B = dp(N, N)
    val A = dp(N, M)
    val gcd = gcd(A, B)
    println("${A / gcd}/${B / gcd}")
}