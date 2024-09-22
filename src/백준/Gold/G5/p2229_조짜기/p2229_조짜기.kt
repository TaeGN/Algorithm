package 백준.Gold.G5.p2229_조짜기

fun main() {
    val N = readln().toInt()
    val list = readln().split(" ").map(String::toInt)
    val diff = Array(N + 1) { IntArray(N + 1) }
    for (i in 1..N) {
        var min = list[i - 1]
        var max = list[i - 1]
        for (j in i..N) {
            min = minOf(min, list[j - 1])
            max = maxOf(max, list[j - 1])
            diff[i][j] = max - min
        }
    }
    val dp = IntArray(N + 1)
    for (i in 1..N) {
        for (j in 0 until i) {
            dp[i] = maxOf(dp[i], dp[j] + diff[j + 1][i])
        }
    }
    println(dp.last())
}