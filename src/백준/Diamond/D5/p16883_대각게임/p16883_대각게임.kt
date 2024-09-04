package 백준.Diamond.D5.p16883_대각게임

const val EMPTY = -1
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().trim().toCharArray() }
    val evenMatrix = Array((N + M + 1) / 2) { CharArray((N - 1) / 2 + (M - 1) / 2 + 1) { ' ' } }
    val oddMatrix = Array((N + M) / 2) { CharArray(N / 2 + M / 2) { ' ' } }
    for (r in 0 until N) {
        for (c in 0 until M) {
            if ((r + c) % 2 == 0) evenMatrix[(r + c) / 2][((c - r) + (N - 1) / 2 * 2) / 2] = matrix[r][c]
            else oddMatrix[(r + c) / 2][((c - r) + N / 2 * 2 - 1) / 2] = matrix[r][c]
        }
    }
    val evenG =
        Array(evenMatrix.size) { Array(evenMatrix.first().size) { Array(evenMatrix.size) { IntArray(evenMatrix.first().size) { EMPTY } } } }
    for (r in evenMatrix.indices) {
        for (c in evenMatrix.first().indices) {
            evenG[r][c][r][c] = if (evenMatrix[r][c] == ' ') 0 else 1
        }
    }
    val oddG =
        Array(oddMatrix.size) { Array(oddMatrix.first().size) { Array(oddMatrix.size) { IntArray(oddMatrix.first().size) { EMPTY } } } }
    for (r in oddMatrix.indices) {
        for (c in oddMatrix.first().indices) {
            oddG[r][c][r][c] = if (oddMatrix[r][c] == ' ') 0 else 1
        }
    }
    fun dfs(sr: Int, sc: Int, er: Int, ec: Int, matrix: Array<CharArray>, G: Array<Array<Array<IntArray>>>): Int {
        if (sr > er || sc > ec) return 0
        if (G[sr][sc][er][ec] != EMPTY) return G[sr][sc][er][ec]
        var flag = 0
        for (r in sr..er) {
            for (c in sc..ec) {
                flag = flag or (1 shl when (matrix[r][c]) {
                    'L' -> dfs(sr, sc, r - 1, ec, matrix, G) xor dfs(r + 1, sc, er, ec, matrix, G)
                    'R' -> dfs(sr, sc, er, c - 1, matrix, G) xor dfs(sr, c + 1, er, ec, matrix, G)
                    'X' -> dfs(sr, sc, r - 1, c - 1, matrix, G) xor dfs(sr, c + 1, r - 1, ec, matrix, G) xor
                            dfs(r + 1, sc, er, c - 1, matrix, G) xor dfs(r + 1, c + 1, er, ec, matrix, G)

                    else -> continue
                })
            }
        }
        for (i in 0 until 30) {
            if ((flag and (1 shl i)) == 0) {
                G[sr][sc][er][ec] = i
                break
            }
        }
        return G[sr][sc][er][ec]
    }

    val result = dfs(0, 0, evenMatrix.size - 1, evenMatrix.first().size - 1, evenMatrix, evenG) xor
            dfs(0, 0, oddMatrix.size - 1, oddMatrix.first().size - 1, oddMatrix, oddG)
    println(if (result != 0) "koosaga" else "cubelover")
}