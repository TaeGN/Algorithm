package AtCoder.ProblemList.Difficulty800_1199.GridIceFloor

const val EMPTY = ' '
const val ICE = '.'
const val ROCK = '#'
val dr = intArrayOf(0, 0, -1, 1)
val dc = intArrayOf(-1, 1, 0, 0)
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().trim().toCharArray() }
    val visited = Array(N) { BooleanArray(M) }
    var result = 1L
    matrix[1][1] = EMPTY
    fun dfs(r: Int = 1, c: Int = 1) {
        visited[r][c] = true
        for (d in dr.indices) {
            var nr = r
            var nc = c
            while (matrix[nr + dr[d]][nc + dc[d]] != ROCK) {
                nr += dr[d]
                nc += dc[d]
                if (matrix[nr][nc] == ICE) {
                    matrix[nr][nc] = EMPTY
                    result++
                }
            }
            if (!visited[nr][nc]) dfs(nr, nc)
        }
    }
    dfs()
    println(result)
}