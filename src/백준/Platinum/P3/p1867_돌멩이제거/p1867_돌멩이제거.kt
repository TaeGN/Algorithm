package 백준.Platinum.P3.p1867_돌멩이제거

const val EMPTY = -1
fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Int>() }
    repeat(K) { readln().split(" ").map(String::toInt).let { outLists[it[0]].add(it[1]) } }
    val visited = BooleanArray(N + 1)
    val rArr = IntArray(N + 1) { EMPTY }
    fun dfs(r: Int): Boolean {
        for (c in outLists[r]) {
            if (visited[c]) continue
            visited[c] = true
            if (rArr[c] == EMPTY || dfs(rArr[c])) {
                rArr[c] = r
                return true
            }
        }
        return false
    }

    var result = 0
    for (r in 1..N) {
        visited.fill(false)
        if (dfs(r)) result++
    }
    println(result)
}