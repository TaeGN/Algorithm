package 백준.Gold.G1.p2213_트리의독립집합

const val EMPTY = -1
fun main() {
    val N = readln().toInt()
    val wArr = readln().split(" ").map(String::toInt)
    val dp = Array(N + 1) { IntArray(2) { EMPTY } }
    val outLists = List(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val (A, B) = readln().split(" ").map(String::toInt)
        outLists[A].add(B)
        outLists[B].add(A)
    }
    val visited = BooleanArray(N + 1)
    fun dfs(from: Int = 1, isPossible: Boolean = true): Pair<Int, Int> {
        if (dp[from][0] != EMPTY) return dp[from][0] to dp[from][1]
        visited[from] = true
        var first = 0
        var second = if (isPossible) wArr[from - 1] else 0
        for (to in outLists[from]) {
            if (visited[to]) continue
            dfs(to, true).let { first += maxOf(it.first, it.second) }
            if (isPossible) dfs(to, false).let { second += it.first }
        }
        return (first to second).also { dp[from][0] = first;dp[from][1] = second }
    }
    dfs()
    visited.fill(false)
    val list = mutableListOf<Int>()
    fun result(from: Int = 1, isPossible: Boolean = true) {
        visited[from] = true
        var nIsPossible = true
        if (isPossible && dp[from][0] <= dp[from][1]) {
            list.add(from)
            nIsPossible = false
        }
        for (to in outLists[from]) {
            if (visited[to]) continue
            result(to, nIsPossible)
        }
    }
    result()
    println(dp[1].max())
    println(list.sorted().joinToString(" "))
}