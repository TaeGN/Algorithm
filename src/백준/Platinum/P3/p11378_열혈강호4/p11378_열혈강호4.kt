package 백준.Platinum.P3.p11378_열혈강호4

const val EMPTY = -1
fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
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

    val queue = ArrayDeque<Int>()
    var count = 0
    for (i in 0 until N) {
        if (dfs(i)) {
            count++
            queue.add(i)
        }
        visited.fill(false)
    }
    var k = 0
    while (queue.isNotEmpty() && k < K) {
        val i = queue.removeFirst()
        if (dfs(i)) {
            k++
            queue.add(i)
        }
        visited.fill(false)
    }
    println(count + k)
}