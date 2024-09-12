package 백준.Diamond.D5.p1886_프리즌브레이크

const val EMPTY_CHAR = '.'
const val EXIT_CHAR = 'D'
const val WALL_CHAR = 'X'
const val EMPTY = -1
const val EXIT = -2
const val WALL = -3
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val originalMatrix = Array(N) { readln().toCharArray() }
    val exitList = mutableListOf<Pair<Int, Int>>()
    val personList = mutableListOf<Pair<Int, Int>>()
    val matrix = Array(N) { IntArray(M) }
    for (r in 0 until N) {
        for (c in 0 until M) {
            when (originalMatrix[r][c]) {
                EMPTY_CHAR -> {
                    matrix[r][c] = personList.size
                    personList.add(r to c)
                }

                EXIT_CHAR -> {
                    matrix[r][c] = EXIT
                    exitList.add(r to c)
                }

                WALL_CHAR -> {
                    matrix[r][c] = WALL
                }
            }
        }
    }
    val timeMatrix = Array(personList.size) { IntArray(exitList.size) { IMPOSSIBLE } }
    fun bfs(exitId: Int) {
        val (sr, sc) = exitList[exitId]
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(sr to sc)
        var count = 0
        while (queue.isNotEmpty()) {
            count++
            repeat(queue.size) {
                val (r, c) = queue.removeFirst()
                for (d in dr.indices) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr in 0 until N && nc in 0 until M && matrix[nr][nc] >= 0) {
                        val personId = matrix[nr][nc]
                        if (timeMatrix[personId][exitId] > count) {
                            timeMatrix[personId][exitId] = count
                            queue.add(nr to nc)
                        }
                    }
                }
            }
        }
    }
    for (exitId in exitList.indices) {
        bfs(exitId)
    }

    val outLists = List(personList.size) { mutableListOf<Int>() }
    val visited = BooleanArray(personList.size)
    val personIdArr = IntArray(N * M * 2 * exitList.size) { EMPTY }
    fun dfs(personId: Int): Boolean {
        if (visited[personId]) return false
        visited[personId] = true
        for (exitId in outLists[personId]) {
            if (personIdArr[exitId] == EMPTY || dfs(personIdArr[exitId])) {
                personIdArr[exitId] = personId
                return true
            }
        }
        return false
    }

    fun isPossible(): Boolean {
        for (personId in personList.indices) {
            visited.fill(false)
            if (!dfs(personId)) return false
        }
        return true
    }

    fun result(): String {
        for (time in 1..(N * M * 2)) {
            for ((personId, timeList) in timeMatrix.withIndex()) {
                for ((exitId, sTime) in timeList.withIndex()) {
                    if (sTime > time) continue
                    outLists[personId].add((time - 1) * exitList.size + exitId)
                }
            }
            personIdArr.fill(EMPTY, 0, time * exitList.size)
            if (isPossible()) return "$time"
        }
        return "impossible"
    }
    println(result())
}