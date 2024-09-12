package 백준.Platinum.P1.p13166_범죄파티

const val EMPTY = -1
fun main() {
    val N = readln().toInt()
    val infoList = List(N) { readln().split(" ").map(String::toInt) }
    val visited = LongArray(N) { EMPTY.toLong() }
    val idArr = IntArray(2 * N + 1)
    fun dfs(id: Int, K: Int, visitedCount: Int): Boolean {
        if (visited[id] >= visitedCount.toLong() * N) return false
        visited[id] = visitedCount.toLong() * N + id
        val (A, Ka, B, Kb) = infoList[id]
        if (K >= Ka && (idArr[A] == EMPTY || dfs(idArr[A], K, visitedCount))) {
            idArr[A] = id
            return true
        }
        if (K >= Kb && (idArr[B] == EMPTY || dfs(idArr[B], K, visitedCount))) {
            idArr[B] = id
            return true
        }
        return false
    }

    fun isPossible(K: Int): Boolean {
        if (K == -1) return true
        idArr.fill(EMPTY)
        visited.fill(EMPTY.toLong())
        for (id in 0 until N) {
            if (!dfs(id, K, id)) return false
        }
        return true
    }

    fun search(start: Int = 0, end: Int = 1000000): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(start)) start else if (isPossible(end)) end else -1
        return if (isPossible(mid)) search(start, mid)
        else search(mid + 1, end)
    }
    println(search())
}