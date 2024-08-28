package 백준.Platinum.P5.p18250_철도여행

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val inDegree = IntArray(N + 1)
    val roadLists = List(N + 1) { mutableListOf<Int>() }
    repeat(M) {
        val (A, B) = readln().split(" ").map(String::toInt)
        roadLists[A].add(B)
        roadLists[B].add(A)
        inDegree[A]++
        inDegree[B]++
    }
    val visited = BooleanArray(N + 1)
    fun dfs(from: Int): Int {
        visited[from] = true
        var oddCount = inDegree[from] % 2
        for (to in roadLists[from]) {
            if (visited[to]) continue
            oddCount += dfs(to)
        }
        return oddCount
    }

    var result = 0
    for (i in 1..N) {
        if (!visited[i] && inDegree[i] != 0) result += maxOf(1, dfs(i) / 2)
    }
    println(result)
}