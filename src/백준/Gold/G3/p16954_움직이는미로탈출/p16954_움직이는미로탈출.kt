package 백준.Gold.G3.p16954_움직이는미로탈출

const val WALL = '#'
val dr = intArrayOf(0, 0, 1, -1, 1, 1, -1, -1)
val dc = intArrayOf(1, -1, 0, 0, 1, -1, 1, -1)
fun main() {
    fun Array<BooleanArray>.add(o: Array<BooleanArray>) {
        for (r in indices) {
            for (c in indices) {
                this[r][c] = this[r][c] || o[r][c]
                o[r][c] = false
            }
        }
    }

    val matrix = Array(8) { readln().toCharArray() }
    val visited = Array(8) { BooleanArray(8) }.apply { this[7][0] = true }
    val nextVisited = Array(8) { BooleanArray(8) }
    repeat(72) { time ->
        for (r in 0 until 8) {
            for (c in 0 until 8) {
                if (visited[r][c]) {
                    if (r - time >= 0 && matrix[r - time][c] == WALL) {
                        visited[r][c] = false
                        continue
                    }
                    for (d in dr.indices) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr in 0 until 8 && nc in 0 until 8) {
                            if (nr - time >= 0 && matrix[nr - time][nc] == WALL) continue
                            nextVisited[nr][nc] = true
                        }
                    }
                }
            }
        }
        visited.add(nextVisited)
    }
    println(if (visited[0][7]) 1 else 0)
}