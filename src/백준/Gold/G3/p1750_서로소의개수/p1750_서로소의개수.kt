package 백준.Gold.G3.p1750_서로소의개수

const val MOD = 10_000_003
fun main() {
    fun gcd(a: Int, b: Int): Int = if (minOf(a, b) == 0) maxOf(a, b)
    else gcd(minOf(a, b), maxOf(a, b) % minOf(a, b))

    val N = readln().toInt()
    val arr = Array(N) { readln().toInt() }
    val dp = IntArray(100001)
    for (a in arr) {
        for (idx in 1..100000) {
            if (dp[idx] > 0) {
                val gcd = gcd(a, idx)
                dp[gcd] = (dp[gcd] + dp[idx]) % MOD
            }
        }
        dp[a] = (dp[a] + 1) % MOD
    }
    println(dp[1])
}