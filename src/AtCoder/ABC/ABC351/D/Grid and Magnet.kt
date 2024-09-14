package AtCoder.ABC.ABC351.D

val dr = intArrayOf(0, 0, 1, -1)
val dc = intArrayOf(1, -1, 0, 0)
fun main() {
    val (H, W) = readln().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().toCharArray() }
    for (r in 0 until H) {
        for (c in 0 until W) {
            if (matrix[r][c] == '#') {
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr in 0 until H && nc in 0 until W && matrix[nr][nc] == '.') matrix[nr][nc] = 'x'
                }
            }
        }
    }
    val visited = Array(H) { IntArray(W) }
    fun dfs(r: Int, c: Int, count: Int): Int {
        visited[r][c] = count
        if (matrix[r][c] == 'x') return 1
        var result = 1
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in 0 until H && nc in 0 until W && visited[nr][nc] != count) {
                result += dfs(nr, nc, count)
            }
        }
        return result
    }

    var result = 0
    for (h in 0 until H) {
        for (w in 0 until W) {
            if (visited[h][w] > 0 || matrix[h][w] == '#') continue
            result = maxOf(result, dfs(h, w, h * W + w + 1))
        }
    }
    println(result)
}