package 백준.Gold.G2.p1949_우수마을

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val nList = readln().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val (A, B) = readln().split(" ").map(String::toInt)
        outLists[A].add(B)
        outLists[B].add(A)
    }
    val dp = Array(N + 1) { IntArray(2) { IMPOSSIBLE } }
    val visited = BooleanArray(N + 1)
    fun dfs(from: Int = 1, isPossible: Boolean = true): Pair<Int, Int> {
        visited[from] = true
        if (dp[from][0] == IMPOSSIBLE) {
            dp[from][0] = 0
            dp[from][1] = if (isPossible) nList[from - 1] else IMPOSSIBLE
            for (to in outLists[from]) {
                if (visited[to]) continue
                dfs(to, true).let { dp[from][0] += maxOf(it.first, it.second) }
                if (isPossible) dfs(to, false).let { dp[from][1] += it.first }
            }
        }

        return dp[from][0] to dp[from][1]
    }
    println(dfs().let { maxOf(it.first, it.second) })
}