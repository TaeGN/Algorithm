package 백준.Platinum.P4.p3860_할로윈묘지

val dx = intArrayOf(0, 0, 1, -1)
val dy = intArrayOf(1, -1, 0, 0)
const val EMPTY = ' '
const val HOLE = 'H'
const val RIP = 'R'
const val IMPOSSIBLE = Long.MAX_VALUE shr 2
const val NEVER = Long.MIN_VALUE shr 2
fun main() {
    val sb = StringBuilder()
    while (true) {
        val (W, H) = readln().trim().split(" ").map(String::toInt)
        if (W == 0) break
        val matrix = Array(W) { CharArray(H) { EMPTY } }
        val list = mutableListOf<Triple<Pair<Int, Int>, Pair<Int, Int>, Int>>()
        val G = readln().toInt()
        repeat(G) { readln().trim().split(" ").map(String::toInt).let { matrix[it[0]][it[1]] = RIP } }
        val E = readln().toInt()
        repeat(E) {
            readln().trim().split(" ").map(String::toInt).let {
                list.add(Triple(it[0] to it[1], it[2] to it[3], it[4]))
                matrix[it[0]][it[1]] = HOLE
            }
        }
        for (x in 0 until W) {
            for (y in 0 until H) {
                if (matrix[x][y] != EMPTY || (x == W - 1 && y == H - 1)) continue
                for (d in dx.indices) {
                    val nx = x + dx[d]
                    val ny = y + dy[d]
                    if (nx in 0 until W && ny in 0 until H && matrix[nx][ny] != RIP) {
                        list.add(Triple(x to y, nx to ny, 1))
                    }
                }
            }
        }
        val dp = Array(W) { LongArray(H) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
        repeat(W * H) { idx ->
            for ((p1, p2, time) in list) {
                val (x1, y1) = p1
                val (x2, y2) = p2
                if (dp[x1][y1] == IMPOSSIBLE) continue
                if (dp[x2][y2] > dp[x1][y1] + time) {
                    if (idx == W * H - 1) dp[x2][y2] = NEVER
                    else dp[x2][y2] = dp[x1][y1] + time
                }
            }
        }
        fun result(): String {
            if (dp.any { row -> row.any { it == NEVER } }) return "Never"
            return if (dp[W - 1][H - 1] == IMPOSSIBLE) "Impossible" else "${dp[W - 1][H - 1]}"
        }
        sb.appendLine(result())
    }
    println(sb)
}