package 백준.Platinum.P4.p2188_축사배정

const val EMPTY = -1
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().split(" ").map(String::toInt) }
    val visited = BooleanArray(M + 1)
    val roomInfo = IntArray(M + 1) { EMPTY }
    fun dfs(cow: Int): Boolean {
        for (i in 1 until matrix[cow].size) {
            val room = matrix[cow][i]
            if (visited[room]) continue
            visited[room] = true
            if (roomInfo[room] == EMPTY || dfs(roomInfo[room])) {
                roomInfo[room] = cow
                return true
            }
        }
        return false
    }

    var result = 0
    for (i in 0 until N) {
        if (dfs(i)) result++
        visited.fill(false)
    }
    println(result)
}