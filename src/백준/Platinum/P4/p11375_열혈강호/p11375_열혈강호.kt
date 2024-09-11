package 백준.Platinum.P4.p11375_열혈강호

const val EMPTY = -1
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().split(" ").map(String::toInt) }
    val idArr = IntArray(M + 1) { EMPTY }
    val visited = BooleanArray(M + 1)
    fun dfs(id: Int): Boolean {
        for (i in 1 until matrix[id].size) {
            val job = matrix[id][i]
            if (visited[job]) continue
            visited[job] = true
            if (idArr[job] == EMPTY || dfs(idArr[job])) {
                idArr[job] = id
                return true
            }
        }
        return false
    }

    var count = 0
    for (id in 0 until N) {
        if (dfs(id)) count++
        visited.fill(false)
    }
    println(count)
}