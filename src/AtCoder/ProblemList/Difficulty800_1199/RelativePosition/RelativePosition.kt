package AtCoder.ProblemList.Difficulty800_1199.RelativePosition

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
const val EMPTY = IMPOSSIBLE + 1
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Triple<Int, Int, Int>>() }
    repeat(M) {
        val (A, B, X, Y) = readln().trim().split(" ").map(String::toInt)
        outLists[A].add(Triple(B, X, Y))
        outLists[B].add(Triple(A, -X, -Y))
    }
    val visited = BooleanArray(N + 1)
    val pos = Array(N + 1) { LongArray(2) { EMPTY } }.apply { this[1][0] = 0; this[1][1] = 0 }
    fun dfs(from: Int = 1) {
        visited[from] = true
        for ((to, X, Y) in outLists[from]) {
            if (pos[to][0] == IMPOSSIBLE) continue
            if (visited[to]) {
                if (pos[to][0] != pos[from][0] + X || pos[to][1] != pos[from][1] + Y) {
                    pos[to][0] = IMPOSSIBLE
                }
                continue
            }
            pos[to][0] = pos[from][0] + X
            pos[to][1] = pos[from][1] + Y
            dfs(to)
            if (pos[from][0] == IMPOSSIBLE) return
        }
    }

    fun dfs2(from: Int) {
        visited[from] = true
        for ((to, _, _) in outLists[from]) {
            pos[to][0] = IMPOSSIBLE
            dfs(to)
        }
    }
    visited.fill(false)
    for (i in 1..N) {
        if (!visited[i] && pos[i][0] == IMPOSSIBLE) dfs2(i)
    }
    dfs()
    val sb = StringBuilder()
    for (i in 1..N) {
        sb.appendLine(if (pos[i][0] >= IMPOSSIBLE) "undecidable" else pos[i].joinToString(" "))
    }
    println(sb)
}