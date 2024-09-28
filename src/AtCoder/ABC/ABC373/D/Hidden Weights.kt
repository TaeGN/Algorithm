package AtCoder.ABC.ABC373.D

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val inLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    val outLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(M) {
        readln().trim().split(" ").map(String::toInt).let {
            inLists[it[1]].add(it[0] to it[2])
            outLists[it[0]].add(it[1] to it[2])
        }
    }
    val result = LongArray(N + 1)
    val visited = BooleanArray(N + 1)
    fun dfs(cur: Int) {
        visited[cur] = true
        for ((prev, weight) in inLists[cur]) {
            if (visited[prev]) continue
            result[prev] = result[cur] - weight
            dfs(prev)
        }
        for ((next, weight) in outLists[cur]) {
            if (visited[next]) continue
            result[next] = result[cur] + weight
            dfs(next)
        }
    }
    for (i in 1..N) {
        if (!visited[i]) dfs(i)
    }
    val sb = StringBuilder()
    for (i in 1..N) {
        sb.append("${result[i]} ")
    }
    println(sb)
}