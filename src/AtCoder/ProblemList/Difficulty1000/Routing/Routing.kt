package AtCoder.ProblemList.Difficulty1000.Routing

val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val N = readln().trim().toInt()
    val matrix = Array(N) { readln().trim().toCharArray() }
    fun bfs(sr: Int, sc: Int, er: Int, ec: Int, type: Char): Int {
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        val visited = Array(N) { BooleanArray(N) }
        val sCount = if (matrix[sr][sc] == type) 0 else 1
        queue.add(Triple(sr, sc, sCount))
        visited[sr][sc] = true

        while (queue.isNotEmpty()) {
            val (r, c, count) = queue.removeFirst()
            if (r == er && c == ec) return count
            for (d in dr.indices) {
                val nr = r + dr[d]
                val nc = c + dc[d]
                if (nr in 0 until N && nc in 0 until N && !visited[nr][nc]) {
                    visited[nr][nc] = true
                    if (matrix[nr][nc] == type) queue.addFirst(Triple(nr, nc, count))
                    else queue.add(Triple(nr, nc, count + 1))
                }
            }
        }
        return -1
    }
    println(bfs(0, 0, N - 1, N - 1, 'R') + bfs(0, N - 1, N - 1, 0, 'B'))
}