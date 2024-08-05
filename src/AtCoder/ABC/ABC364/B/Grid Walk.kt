package AtCoder.ABC.ABC364.B

const val EMPTY = '.'
val dr = intArrayOf(0, 0, -1, 1)
val dc = intArrayOf(-1, 1, 0, 0)
fun main() {
    val (H, W) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    var (r, c) = readln().split(" ").map(String::toInt).let { it[0] - 1 to it[1] - 1 }
    val matrix = List(H) { CharArray(W) }
    repeat(H) { r ->
        val input = readln()
        repeat(W) { c ->
            matrix[r][c] = input[c]
        }
    }
    val query = readln()
    for (key in query) {
        val d = when (key) {
            'L' -> 0
            'R' -> 1
            'U' -> 2
            'D' -> 3
            else -> throw IllegalArgumentException()
        }
        val nr = r + dr[d]
        val nc = c + dc[d]
        if (nr in 0 until H && nc in 0 until W && matrix[nr][nc] == EMPTY) {
            r = nr
            c = nc
        }
    }
    println("${r + 1} ${c + 1}")
}