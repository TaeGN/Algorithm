package AtCoder.ABC.ABC382.D

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    var totalCount = 0
    val sb = StringBuilder()
    fun dfs(n: Int = 0, remainedCount: Int = M - (10 * N - 9), arr: IntArray = IntArray(N)) {
        if (n == N) {
            totalCount++
            sb.appendLine(arr.joinToString(" "))
            return
        }
        for (count in 0..remainedCount) {
            arr[n] = arr.getOrElse(n - 1) { -9 } + 10 + count
            dfs(n + 1, remainedCount - count, arr)
        }
    }
    dfs()
    println(totalCount)
    println(sb)
}