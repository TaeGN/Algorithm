package 백준.Gold.G5.p13398_연속합2

fun main() {
    val N = readln().toInt()
    val dp = IntArray(2) { Int.MIN_VALUE shr 2 }
    var result = Int.MIN_VALUE shr 2
    for (a in readln().split(" ").map(String::toInt)) {
        dp[1] = maxOf(dp[0], dp[1] + a)
        dp[0] = maxOf(a, dp[0] + a)
        result = maxOf(result, dp.max())
    }
    println(result)
}