package AtCoder.ProblemList.Difficulty800_1199.AvoidEyeContact

const val EMPTY = '.'
const val WALL = '#'
const val VISION = ' '
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() {
    val (H, W) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().toCharArray() }
    val dMap = mapOf('>' to 0, 'v' to 1, '<' to 2, '^' to 3)
    var sr = 0
    var sc = 0
    for (i in 0 until H) {
        for (j in 0 until W) {
            if (matrix[i][j] in dMap) {
                val d = dMap[matrix[i][j]]!!
                matrix[i][j] = WALL
                var r = i + dr[d]
                var c = j + dc[d]
                while (r in 0 until H && c in 0 until W && (matrix[r][c] == EMPTY || matrix[r][c] == VISION)) {
                    matrix[r][c] = VISION
                    r += dr[d]
                    c += dc[d]
                }
            } else if (matrix[i][j] == 'S') {
                sr = i
                sc = j
            }
        }
    }
    fun result(): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(sr to sc)
        val visited = Array(H) { BooleanArray(W) }.apply { this[sr][sc] = true }
        var count = 0
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val (r, c) = queue.removeFirst()
                if (matrix[r][c] == 'G') return count
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr in 0 until H && nc in 0 until W && !visited[nr][nc] && (matrix[nr][nc] != WALL && matrix[nr][nc] != VISION)) {
                        visited[nr][nc] = true
                        queue.add(nr to nc)
                    }
                }
            }
            count++
        }
        return -1
    }
    println(result())
}