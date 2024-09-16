package 백준.Platinum.P5.p14601_샤워실바닥깔기_Large

val dr = intArrayOf(0, 0, 1, 1)
val dc = intArrayOf(0, 1, 0, 1)
fun main() {
    val K = readln().toInt()
    val (r, c) = readln().split(" ").map(String::toInt).let { ((1 shl K) - it[1]) to (it[0] - 1) }
    val matrix = Array(1 shl K) { IntArray(1 shl K) }.apply { this[r][c] = -1 }
    var count = 1
    fun dfs(sr: Int, sc: Int, k: Int, tr: Int, tc: Int) {
        if (k == 1) {
            for (i in 0 until 2) {
                for (j in 0 until 2) {
                    if (sr + i != tr || sc + j != tc) matrix[sr + i][sc + j] = count
                }
            }
            count++
            return
        }
        val size = 1 shl k
        val nk = k - 1
        val nSize = 1 shl nk
        val targetD = when {
            tr in sr until (sr + nSize) && tc in sc until (sc + nSize) -> 0
            tr in sr until (sr + nSize) && tc in (sc + nSize) until (sc + size) -> 1
            tr in (sr + nSize) until (sr + size) && tc in sc until (sc + nSize) -> 2
            tr in (sr + nSize) until (sr + size) && tc in (sc + nSize) until (sc + size) -> 3
            else -> throw IllegalArgumentException()
        }
        for (d in dr.indices) {
            if (d != targetD) matrix[(sr + nSize - 1) + dr[d]][(sc + nSize - 1) + dc[d]] = count
        }
        count++
        for (d in dr.indices) {
            val nr = sr + nSize * dr[d]
            val nc = sc + nSize * dc[d]
            if (d == targetD) dfs(nr, nc, nk, tr, tc)
            else dfs(nr, nc, nk, (sr + nSize - 1) + dr[d], (sc + nSize - 1) + dc[d])
        }
    }
    dfs(0, 0, K, r, c)
    println(matrix.joinToString("\n") { it.joinToString(" ") })
}