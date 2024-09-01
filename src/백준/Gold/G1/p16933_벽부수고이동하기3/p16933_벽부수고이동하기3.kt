package 백준.Gold.G1.p16933_벽부수고이동하기3

const val WALL = '1'
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val (N, M, K) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().trim().toCharArray() }
    val visited = Array(N) { Array(M) { Array(K + 1) { BooleanArray(2) } } }
    fun bfs(): Int {
        val queue = ArrayDeque<IntArray>()
        visited[0][0][0][0] = true
        queue.add(intArrayOf(0, 0, 0, 0))
        var dist = 0
        while (queue.isNotEmpty()) {
            dist++
            repeat(queue.size) {
                val (r, c, k, type) = queue.removeFirst()
                if (r == N - 1 && c == M - 1) return dist
                val nType = (type + 1) % 2
                if (!visited[r][c][k][nType]) {
                    visited[r][c][k][nType] = true
                    queue.add(intArrayOf(r, c, k, nType))
                }
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr in 0 until N && nc in 0 until M) {
                        if (matrix[nr][nc] == WALL) {
                            val nk = k + 1
                            if (type == 0 && nk <= K && !visited[nr][nc][nk][nType]) {
                                visited[nr][nc][nk][nType] = true
                                queue.add(intArrayOf(nr, nc, nk, nType))
                            }
                        } else {
                            val nk = k
                            if (!visited[nr][nc][nk][nType]) {
                                visited[nr][nc][nk][nType] = true
                                queue.add(intArrayOf(nr, nc, nk, nType))
                            }
                        }
                    }
                }
            }
        }
        return -1
    }
    println(bfs())
}