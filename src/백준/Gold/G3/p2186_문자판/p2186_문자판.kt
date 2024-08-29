package 백준.Gold.G3.p2186_문자판

val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)

fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().toCharArray() }
    val S = readln()
    val dp = Array(N) { Array(M) { IntArray(S.length) } }
    matrix.forEachIndexed { r, chars -> chars.forEachIndexed { c, elm -> if (elm == S[0]) dp[r][c][0] = 1 } }
    for (i in 0 until S.length - 1) {
        for (r in 0 until N) {
            for (c in 0 until M) {
                if (dp[r][c][i] != 0) {
                    for (d in dr.indices) {
                        for (k in 1..K) {
                            val nr = r + dr[d] * k
                            val nc = c + dc[d] * k
                            if (nr !in 0 until N || nc !in 0 until M) break
                            if (matrix[nr][nc] == S[i + 1]) dp[nr][nc][i + 1] += dp[r][c][i]
                        }
                    }
                }
            }
        }
    }
    println(dp.sumOf { row -> row.sumOf { col -> col.last() } })
}