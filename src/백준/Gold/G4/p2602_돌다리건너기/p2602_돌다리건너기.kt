package 백준.Gold.G4.p2602_돌다리건너기

fun main() {
    val target = readln()
    val bridge = Array(2) { readln() }
    val dp = Array(target.length) { IntArray(2) }
    for (i in bridge[0].indices) {
        for (j in (target.length - 1) downTo 0) {
            for (k in 0..1) {
                if (target[j] == bridge[k][i]) dp[j][k] += dp.getOrNull(j - 1)?.get((k + 1) % 2) ?: 1
            }
        }
    }
    println(dp.last().sum())
}