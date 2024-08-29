package 백준.Gold.G3.p2151_거울설치

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val DOOR = '#'
const val MIRROR = '!'
const val WALL = '*'
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() {
    val N = readln().toInt()
    val matrix = Array(N) { readln().toCharArray() }
    val doorList = mutableListOf<Pair<Int, Int>>()
    matrix.forEachIndexed { r, chars -> chars.forEachIndexed { c, elm -> if (elm == DOOR) doorList.add(r to c) } }
    val dp = Array(N) { Array(N) { IntArray(4) { IMPOSSIBLE } } }
    fun dfs(r: Int, c: Int, d: Int) {
        val nr = r + dr[d]
        val nc = c + dc[d]
        if (nr in 0 until N && nc in 0 until N && matrix[nr][nc] != WALL) {
            if (dp[nr][nc][d] > dp[r][c][d]) {
                dp[nr][nc][d] = dp[r][c][d]
                dfs(nr, nc, d)
            }
            if (matrix[nr][nc] == MIRROR) {
                val nd1 = (d + 1) % 4
                val nd2 = (d + 3) % 4
                if (dp[nr][nc][nd1] > dp[r][c][d]) {
                    dp[nr][nc][nd1] = dp[r][c][d] + 1
                    dfs(nr, nc, nd1)
                }
                if (dp[nr][nc][nd2] > dp[r][c][d]) {
                    dp[nr][nc][nd2] = dp[r][c][d] + 1
                    dfs(nr, nc, nd2)
                }
            }
        }
    }

    val r = doorList[0].first
    val c = doorList[0].second
    for (d in dr.indices) {
        val nr = r + dr[d]
        val nc = c + dc[d]
        if (nr in 0 until N && nc in 0 until N && matrix[nr][nc] != WALL) {
            dp[r][c][d] = 0
            dfs(r, c, d)
        }
    }
    val er = doorList[1].first
    val ec = doorList[1].second
    println(dp[er][ec].min())
}