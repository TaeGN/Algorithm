package AtCoder.ProblemList.Difficulty800_1199.Prerequisites

fun main() {
    val N = readln().trim().toInt()
    val inLists = List(N + 1) { mutableListOf<Int>() }
    val outLists = List(N + 1) { mutableListOf<Int>() }
    val inDegree = IntArray(N + 1)
    repeat(N) { idx ->
        val input = readln().trim().split(" ").map(String::toInt)
        inDegree[idx + 1] = input[0]
        for (i in 1 until input.size) {
            inLists[idx + 1].add(input[i])
            outLists[input[i]].add(idx + 1)
        }
    }
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(N + 1)
    queue.add(1)
    while (queue.isNotEmpty()) {
        val from = queue.removeFirst()
        visited[from] = true
        for (to in inLists[from]) {
            if (!visited[to]) queue.add(to)
        }
    }
    for (i in 1..N) {
        if (inDegree[i] == 0) queue.add(i)
    }
    val sb = StringBuilder()
    while (queue.isNotEmpty()) {
        val from = queue.removeFirst()
        if (from == 1) break
        if (visited[from]) sb.append("$from ")
        for (to in outLists[from]) {
            if (--inDegree[to] == 0) queue.add(to)
        }
    }
    println(sb)
}