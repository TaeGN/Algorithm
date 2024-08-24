package AtCoder.ABC.ABC368.D

fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val roadLists = List(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val (A, B) = readln().split(" ").map(String::toInt)
        roadLists[A].add(B)
        roadLists[B].add(A)
    }
    val kSet = readln().split(" ").map(String::toInt).toSet()
    val countArr = IntArray(N + 1)
    val visited = BooleanArray(N + 1)
    fun dfs(from: Int): Int {
        visited[from] = true
        var count = if (from in kSet) 1 else 0
        for (to in roadLists[from]) {
            if (visited[to]) continue
            count += dfs(to)
        }
        return count.also { countArr[from] = it }
    }

    val root = kSet.first()
    dfs(root)
    println(N - countArr.count { it == 0 } + 1)
}