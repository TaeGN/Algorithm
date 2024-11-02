package AtCoder.ABC.ABC378.D

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val (H, W, K) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().toCharArray() }
    val visited = Array(H) { BooleanArray(W) }
    var result = 0L
    fun dfs(r: Int, c: Int, k: Int = 0) {
        if (k == K) {
            result++
            return
        }
        visited[r][c] = true
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in 0 until H && nc in 0 until W && !visited[nr][nc] && matrix[nr][nc] == '.') {
                dfs(nr, nc, k + 1)
            }
        }
        visited[r][c] = false
    }
    for (i in 0 until H) {
        for (j in 0 until W) {
            if (matrix[i][j] == '.') dfs(i, j, 0)
        }
    }
    println(result)
}