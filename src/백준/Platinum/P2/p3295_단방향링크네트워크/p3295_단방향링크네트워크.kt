package 백준.Platinum.P2.p3295_단방향링크네트워크

const val EMPTY = -1
fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M) = readln().split(" ").map(String::toInt)
        val outLists = List(N) { mutableListOf<Int>() }
        repeat(M) { readln().split(" ").map(String::toInt).let { outLists[it[0]].add(it[1]) } }
        val visited = BooleanArray(N)
        val idArr = IntArray(N) { EMPTY }
        fun dfs(from: Int): Boolean {
            for (to in outLists[from]) {
                if (visited[to]) continue
                visited[to] = true
                if (idArr[to] == EMPTY || dfs(idArr[to])) {
                    idArr[to] = from
                    return true
                }
            }
            return false
        }

        var result = 0
        for (i in 0 until N) {
            visited.fill(false)
            if (dfs(i)) result++
        }
        sb.appendLine(result)
    }
    println(sb)
}