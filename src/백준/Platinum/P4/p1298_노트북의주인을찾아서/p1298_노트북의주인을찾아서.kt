package 백준.Platinum.P4.p1298_노트북의주인을찾아서

const val EMPTY = -1
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Int>() }
    repeat(M) {
        val (a, b) = readln().split(" ").map(String::toInt)
        outLists[a].add(b)
    }
    val visited = BooleanArray(N + 1)
    val selected = IntArray(N + 1) { EMPTY }
    fun dfs(from: Int): Boolean {
        if (visited[from]) return false
        visited[from] = true
        for (to in outLists[from]) {
            if (selected[to] == EMPTY || dfs(selected[to])) {
                selected[to] = from
                return true
            }
        }
        return false
    }
    var result = 0
    for(i in 1..N) {
        visited.fill(false)
        if(dfs(i)) result++
    }
    println(result)
}