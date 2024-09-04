package 백준.Diamond.D5.p26102_CardGame

const val EMPTY = -1
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val evenMatrix = Array((N + M) / 2) { CharArray((N + M) / 2) { ' ' } }
    val oddMatrix = Array((N + M) / 2) { CharArray((N + M) / 2) { ' ' } }
    for (r in 0 until N) {
        for ((c, elm) in readln().trim().withIndex()) {
            if ((r + c) % 2 == 0) evenMatrix[(r + c) / 2][((c - r) + (N - 1) / 2 * 2) / 2] = elm
            else oddMatrix[(r + c) / 2][((c - r) + N / 2 * 2 - 1) / 2] = elm
        }
    }

    val eRLen = evenMatrix.size
    val eCLen = evenMatrix.first().size
    val evenG = Array(eRLen) { Array(eCLen) { Array(eRLen) { IntArray(eCLen) { EMPTY } } } }
    val oRLen = oddMatrix.size
    val oCLen = oddMatrix.first().size
    val oddG = Array(oRLen) { Array(oCLen) { Array(oRLen) { IntArray(oCLen) { EMPTY } } } }
    fun dfs(sr: Int, sc: Int, er: Int, ec: Int, matrix: Array<CharArray>, G: Array<Array<Array<IntArray>>>): Int {
        if (sr > er || sc > ec) return 0
        if (G[sr][sc][er][ec] == EMPTY) {
            var flag = 0L
            for (r in sr..er) {
                for (c in sc..ec) {
                    flag = flag or (1L shl when (matrix[r][c]) {
                        'R' -> dfs(sr, sc, r - 1, ec, matrix, G) xor dfs(r + 1, sc, er, ec, matrix, G)
                        'B' -> dfs(sr, sc, er, c - 1, matrix, G) xor dfs(sr, c + 1, er, ec, matrix, G)
                        'G' -> dfs(sr, sc, r - 1, c - 1, matrix, G) xor dfs(sr, c + 1, r - 1, ec, matrix, G) xor
                                dfs(r + 1, sc, er, c - 1, matrix, G) xor dfs(r + 1, c + 1, er, ec, matrix, G)

                        else -> continue
                    })
                }
            }
            for (i in 0 until 60) {
                if ((flag and (1L shl i)) == 0L) {
                    G[sr][sc][er][ec] = i
                    break
                }
            }
        }
        return G[sr][sc][er][ec]
    }

    val result = dfs(0, 0, eRLen - 1, eCLen - 1, evenMatrix, evenG) xor dfs(0, 0, oRLen - 1, oCLen - 1, oddMatrix, oddG)
    println(if (result != 0) "W" else "L")
}