package 백준.Gold.G1.p5022_연결

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val EMPTY = -1
const val END = -2
val dr = intArrayOf(0, 0, 1, -1)
val dc = intArrayOf(1, -1, 0, 0)
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    fun hash(r: Int, c: Int) = r * (M + 1) + c
    fun Int.r() = this / (M + 1)
    fun Int.c() = this % (M + 1)
    val parentArr = Array(N + 1) { IntArray(M + 1) { EMPTY } }
    val queue = ArrayDeque<Int>()
    fun add(r: Int, c: Int): Boolean {
        if (r in 0..N && c in 0..M && parentArr[r][c] == EMPTY) {
            queue.add(hash(r, c))
            return true
        }
        return false
    }

    fun bfs(sr: Int, sc: Int, er: Int, ec: Int): Int {
        queue.clear()
        parentArr[sr][sc] = EMPTY
        parentArr[er][ec] = EMPTY
        if (add(sr, sc)) parentArr[sr][sc] = END
        var time = 0
        while (queue.isNotEmpty()) {
            time++
            repeat(queue.size) {
                val hash = queue.removeFirst()
                val r = hash.r()
                val c = hash.c()
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (add(nr, nc)) parentArr[nr][nc] = hash
                    if (nr == er && nc == ec) return time
                }
            }
        }
        return IMPOSSIBLE
    }

    fun setParent(sr: Int, sc: Int) {
        var r = sr
        var c = sc
        while (parentArr[r][c] >= 0) {
            val hash = parentArr[r][c]
            parentArr[r][c] = END
            r = hash.r()
            c = hash.c()
        }
        for (i in parentArr.indices) {
            for (j in parentArr[i].indices) {
                parentArr[i][j] = if (parentArr[i][j] == END) END else EMPTY
            }
        }
    }

    fun initParent() {
        parentArr.forEach { it.fill(EMPTY) }
    }

    fun minLen(sr1: Int, sc1: Int, er1: Int, ec1: Int, sr2: Int, sc2: Int, er2: Int, ec2: Int): Int {
        initParent()
        parentArr[sr2][sc2] = END
        parentArr[er2][ec2] = END
        var totalLen1 = bfs(sr1, sc1, er1, ec1)
        setParent(er1, ec1)
        totalLen1 += bfs(sr2, sc2, er2, ec2)
        initParent()
        parentArr[sr1][sc1] = END
        parentArr[er1][ec1] = END
        var totalLen2 = bfs(sr2, sc2, er2, ec2)
        setParent(er2, ec2)
        totalLen2 += bfs(sr1, sc1, er1, ec1)
        return minOf(totalLen1, totalLen2)
    }

    val (sr1, sc1) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    val (er1, ec1) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    val (sr2, sc2) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    val (er2, ec2) = readln().split(" ").map(String::toInt).let { it[0] to it[1] }
    val minLen = minLen(sr1, sc1, er1, ec1, sr2, sc2, er2, ec2)
    println(if (minLen >= IMPOSSIBLE) "IMPOSSIBLE" else minLen)
}