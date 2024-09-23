package 백준.Gold.G3.p2281_데스노트

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val dp = LongArray(M + 1) { IMPOSSIBLE }.apply { this[M] = 0 }
    val nDp = LongArray(M + 1) { IMPOSSIBLE }
    repeat(N) {
        val len = readln().toInt()
        for (i in 0..M) {
            nDp[M - len] = minOf(nDp[M - len], dp[i] + i * i)
            val ni = if (i == M) i - len else i - len - 1
            if (ni >= 0) {
                nDp[ni] = minOf(nDp[ni], dp[i])
            }
        }
        for (i in 0..M) {
            dp[i] = nDp[i]
            nDp[i] = IMPOSSIBLE
        }
    }
    println(dp.min())
}