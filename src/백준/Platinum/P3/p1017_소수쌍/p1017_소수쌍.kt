package 백준.Platinum.P3.p1017_소수쌍

const val EMPTY = -1
fun main() {
    val isNotPrime = BooleanArray(2001).apply { this[0] = true; this[1] = true }
    for (i in 2 until isNotPrime.size) {
        for (j in (i * i) until isNotPrime.size step i) {
            isNotPrime[j] = true
        }
    }
    val N = readln().toInt()
    val list = readln().split(" ").map(String::toInt)
    val matrix = Array(N) { mutableListOf<Int>() }
    for (i in 0 until N) {
        for (j in (i + 1) until N) {
            if (isNotPrime[list[i] + list[j]]) continue
            matrix[i].add(j)
            matrix[j].add(i)
        }
    }
    val idArr = IntArray(N) { EMPTY }
    val visited = BooleanArray(N)
    fun dfs(id1: Int): Boolean {
        for (id2 in matrix[id1]) {
            if (visited[id2]) continue
            visited[id2] = true
            if (idArr[id2] == EMPTY || dfs(idArr[id2])) {
                idArr[id2] = id1
                return true
            }
        }
        return false
    }

    val result = mutableListOf<Int>()
    for (id in matrix.first()) {
        idArr.fill(EMPTY)
        fun init() {
            visited.fill(false)
            visited[0] = true
            visited[id] = true
        }

        var count = 0
        for (i in 1 until N) {
            if (i == id) continue
            init()
            if (dfs(i)) count++
        }
        if (count == N - 2) result.add(list[id])
    }
    println(result.let { if (it.isEmpty()) -1 else it.sorted().joinToString(" ") })
}