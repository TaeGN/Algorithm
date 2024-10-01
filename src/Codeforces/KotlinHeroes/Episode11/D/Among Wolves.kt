package Codeforces.KotlinHeroes.Episode11.D

val dr = intArrayOf(1, -1, 0, 0)
var dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, H, B) = readln().trim().split(" ").map(String::toInt)
        val matrix = Array(2) { readln().trim().toCharArray() }
        var sr = 0
        var sc = 0
        for (r in 0 until 2) {
            for (c in 0 until N) {
                if (matrix[r][c] == 'S') {
                    sr = r
                    sc = c
                }
            }
        }
        var result = 0L
        var aroundCount = 0
        for (d in dr.indices) {
            val r = sr + dr[d]
            val c = sc + dc[d]
            if (r in 0 until 2 && c in 0 until N) {
                aroundCount++
                if (matrix[r][c] == 'W') {
                    result += H
                    matrix[r][c] = '.'
                }
            }
        }
        val totalWolfCount = matrix.sumOf { it.count { c -> c == 'W' } }
        var leftWolfCount = 0
        for (r in 0 until 2) {
            for (c in 0 until sc) {
                if (matrix[r][c] == 'W') leftWolfCount++
            }
        }
        val rightWolfCount = totalWolfCount - leftWolfCount
        result += minOf(
            B.toLong() * aroundCount,
            H.toLong() * totalWolfCount,
            B.toLong() * 2 + H.toLong() * leftWolfCount,
            B.toLong() * 2 + H.toLong() * rightWolfCount
        )
        sb.appendLine(result)
    }
    println(sb)
}