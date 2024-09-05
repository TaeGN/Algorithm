package 백준.Gold.G3.p14442_벽부수고이동하기2

const val WALL = '1'
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)

fun main() {
    val (N, M, K) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().trim().toCharArray() }
    val visited = Array(N) { Array(M) { BooleanArray(K + 1) } }

    fun bfs(sr: Int = 0, sc: Int = 0, sk: Int = 0): Int {
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        queue.add(Triple(sr, sc, sk))
        visited[sr][sc][sk] = true
        var dist = 1
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val (r, c, k) = queue.removeFirst()
                if (r == N - 1 && c == M - 1) return dist
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr in 0 until N && nc in 0 until M) {
                        val nk = k + (if (matrix[nr][nc] == WALL) 1 else 0)
                        if (nk <= K && !visited[nr][nc][nk]) {
                            queue.add(Triple(nr, nc, nk))
                            visited[nr][nc][nk] = true
                        }
                    }
                }
            }
            dist++
        }
        return -1
    }
    println(bfs())
}