package AtCoder.ProblemList.Difficulty400_799.EraseLeaves

const val EMPTY = Int.MAX_VALUE shr 2
fun main() {
    val N = readln().toInt()
    val outLists = List(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val (A, B) = readln().trim().split(" ").map(String::toInt)
        outLists[A].add(B)
        outLists[B].add(A)
    }
    val dp = IntArray(N + 1) { EMPTY }
    fun dfs(from: Int = 1): Int {
        dp[from] = 1
        for (to in outLists[from]) {
            if (dp[to] == EMPTY) dp[from] += dfs(to)
        }
        return dp[from]
    }
    dfs()
    println(outLists[1].sumOf { dp[it] } - outLists[1].maxOf { dp[it] } + 1)
}