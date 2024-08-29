package 백준.Gold.G2.p2931_가스관

val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
const val LEFT = 3
const val RIGHT = 2
const val DOWN = 0
const val UP = 1
fun main() {
    val (R, C) = readln().split(" ").map(String::toInt)
    val matrix = Array(R) { readln().toCharArray() }
    var start = 0 to 0
    matrix.forEachIndexed { r, chars -> chars.forEachIndexed { c, elm -> if (elm == 'M') start = r to c } }
    fun dfs(r: Int, c: Int, d: Int): Pair<Int, Int> {
        val nr = r + dr[d]
        val nc = c + dc[d]
        val nd = when (matrix[nr][nc]) {
            '|', '-', '+' -> d
            '1' -> if (d == LEFT) DOWN else RIGHT
            '2' -> if (d == LEFT) UP else RIGHT
            '3' -> if (d == RIGHT) UP else LEFT
            '4' -> if (d == RIGHT) DOWN else LEFT
            else -> return nr to nc
        }
        return dfs(nr, nc, nd)
    }

    fun isPossible(r: Int, c: Int, d: Int) = when (d) {
        0 -> matrix[r][c] in listOf('|', '+', '2', '3')
        1 -> matrix[r][c] in listOf('|', '+', '1', '4')
        2 -> matrix[r][c] in listOf('-', '+', '3', '4')
        3 -> matrix[r][c] in listOf('-', '+', '1', '2')
        else -> throw IllegalArgumentException()
    }

    fun dfs(r: Int, c: Int): Pair<Int, Int> {
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in 0 until R && nc in 0 until C && matrix[nr][nc] != '.') {
                if (isPossible(nr, nc, d)) {
                    return dfs(r, c, d)
                }
            }
        }
        throw IllegalArgumentException()
    }
    val (r, c) = dfs(start.first, start.second)
    val arr = BooleanArray(4)
    for (d in dr.indices) {
        val nr = r + dr[d]
        val nc = c + dc[d]
        if (nr in 0 until R && nc in 0 until C && matrix[nr][nc] != '.') {
            if (isPossible(nr, nc, d)) arr[d] = true
        }
    }
    val block = when {
        arr[UP] && arr[DOWN] && arr[LEFT] && arr[RIGHT] -> '+'
        arr[UP] && arr[DOWN] -> '|'
        arr[LEFT] && arr[RIGHT] -> '-'
        arr[RIGHT] && arr[DOWN] -> '1'
        arr[RIGHT] && arr[UP] -> '2'
        arr[LEFT] && arr[UP] -> '3'
        arr[LEFT] && arr[DOWN] -> '4'
        else -> throw IllegalArgumentException()
    }
    println("${r + 1} ${c + 1} $block")
}