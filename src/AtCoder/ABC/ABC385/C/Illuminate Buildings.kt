package AtCoder.ABC.ABC385.C

fun main() {
    val N = readln().trim().toInt()
    val H = readln().trim().split(" ").map(String::toInt)
    val dp = List(N) { IntArray(N) { 1 } }
    for (idx in 0 until N) {
        for (dist in 1..idx) {
            if (H[idx] == H[idx - dist]) dp[idx][dist] = dp[idx - dist][dist] + 1
        }
    }
    println(dp.maxOf { it.max() })
}