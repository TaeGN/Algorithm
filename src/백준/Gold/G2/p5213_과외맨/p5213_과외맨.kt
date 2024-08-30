package 백준.Gold.G2.p5213_과외맨

const val EMPTY = -1
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val N = readln().toInt()
    val matrix = Array(N) { IntArray(2 * N) { EMPTY } }
    for (r in 0 until N) {
        if (r % 2 == 0) {
            for (c in 0 until N) {
                val (A, B) = readln().split(" ").map(String::toInt)
                matrix[r][c * 2] = A
                matrix[r][c * 2 + 1] = B
            }
        } else {
            for (c in 0 until (N - 1)) {
                val (A, B) = readln().split(" ").map(String::toInt)
                matrix[r][c * 2 + 1] = A
                matrix[r][c * 2 + 2] = B
            }
        }
    }

    val queue = ArrayDeque<Pair<Int, Int>>()
    val visited = Array(N) { BooleanArray(2 * N) }
    val parentArr = IntArray(N * N - N / 2 + 1) { EMPTY }.apply { this[1] = 0 }
    fun getId(r: Int, c: Int): Int = r * N - r / 2 + (if (r % 2 == 0) c else c - 1) / 2 + 1
    fun isValid(r: Int, c: Int) = r in 0 until N && c in (if (r % 2 == 0) 0 until 2 * N else 1 until 2 * N - 1)
    fun add(r: Int, c: Int): Boolean {
        if (!visited[r][c]) {
            val c1 = if (r % 2 == 0) c / 2 * 2 else (c - 1) / 2 * 2 + 1
            val c2 = c1 + 1
            queue.add(r to c1)
            queue.add(r to c2)
            visited[r][c1] = true
            visited[r][c2] = true
            return true
        }
        return false
    }
    add(0, 0)
    while (queue.isNotEmpty()) {
        val (r, c) = queue.removeFirst()
        val id = getId(r, c)
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (isValid(nr, nc) && matrix[r][c] == matrix[nr][nc] && add(nr, nc)) parentArr[getId(nr, nc)] = id
        }
    }
    var id = parentArr.indexOfLast { it != EMPTY }
    var count = 0
    val sb = StringBuilder()
    while (id > 0) {
        sb.insert(0, "$id ")
        count++
        id = parentArr[id]
    }
    println(count)
    println(sb)
}