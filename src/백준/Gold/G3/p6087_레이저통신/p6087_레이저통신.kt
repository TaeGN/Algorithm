package 백준.Gold.G3.p6087_레이저통신

import java.util.PriorityQueue

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val WALL = '*'
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val (W, H) = readln().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().toCharArray() }
    val dp = Array(H) { Array(W) { IntArray(4) { IMPOSSIBLE } } }
    val mirrorList = mutableListOf<Pair<Int, Int>>()
    matrix.forEachIndexed { r, chars -> chars.forEachIndexed { c, elm -> if (elm == 'C') mirrorList.add(r to c) } }
    val sr = mirrorList[0].first
    val sc = mirrorList[0].second
    val pq = PriorityQueue<IntArray>(compareBy { it[0] })
    for (d in dr.indices) {
        dp[sr][sc][d] = 0
        pq.add(intArrayOf(0, sr, sc, d))
    }
    while (pq.isNotEmpty()) {
        val (count, r, c, d) = pq.poll()
        if (dp[r][c][d] < count) continue
        for (nd in dr.indices) {
            val nr = r + dr[nd]
            val nc = c + dc[nd]
            val nCount = count + (if (nd == d) 0 else 1)
            if (nr in 0 until H && nc in 0 until W && matrix[nr][nc] != WALL && dp[nr][nc][nd] > nCount) {
                dp[nr][nc][nd] = nCount
                pq.add(intArrayOf(nCount, nr, nc, nd))
            }
        }
    }

    val er = mirrorList[1].first
    val ec = mirrorList[1].second
    println(dp[er][ec].min())
}