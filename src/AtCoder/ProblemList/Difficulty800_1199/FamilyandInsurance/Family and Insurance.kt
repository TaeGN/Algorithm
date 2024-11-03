package AtCoder.ProblemList.Difficulty800_1199.FamilyandInsurance

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Int>() }
    val P = readln().trim().split(" ").map(String::toInt)
    for (i in P.indices) {
        outLists[P[i]].add(i + 2)
    }
    val Y = IntArray(N + 1) { -1 }
    repeat(M) {
        val (x, y) = readln().trim().split(" ").map(String::toInt)
        Y[x] = maxOf(Y[x], y)
    }
    fun dfs(from: Int = 1, y: Int = Y[from]): Int {
        var result = if (y >= 0) 1 else 0
        for (to in outLists[from]) {
            result += dfs(to, maxOf(y - 1, Y[to]))
        }
        return result
    }
    println(dfs())
}