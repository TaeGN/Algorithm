package 백준.Gold.G4.p27361_막대자르기

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, K, A, B) = readln().trim().split(" ").map(String::toInt)
        val S = readln().trim().split(" ").map(String::toInt)
        val dp = LongArray(K + 1) { IMPOSSIBLE }.apply { this[minOf(K, S.count { it == 1 })] = 0 }
        for (s in S) {
            if (s == 1) continue
            for (i in K downTo 0) {
                if (dp[i] == IMPOSSIBLE) continue
                dp[minOf(K, i + s)] = minOf(dp[minOf(K, i + s)], dp[i] + A.toLong() * (s - 1) * (s - 1) + B)
            }
        }
        sb.appendLine(dp[K])
    }
    println(sb)
}