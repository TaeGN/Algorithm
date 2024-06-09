package 백준.Silver.S2.p18352_특정거리의도시찾기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val cityCount = readInt()
    val roadCount = readInt()
    val targetDistance = readInt()
    val startId = readInt()

    val roadList = List(cityCount + 1) { mutableListOf<Int>() }
    repeat(roadCount) {
        val from = readInt()
        val to = readInt()
        roadList[from].add(to)
    }

    val minDistance = IntArray(cityCount + 1) { Int.MAX_VALUE }
    val visited = BooleanArray(cityCount + 1)
    val queue = ArrayDeque<Int>()
    minDistance[startId] = 0
    visited[startId] = true
    queue.add(startId)
    while (queue.isNotEmpty()) {
        val curId = queue.removeFirst()
        for (nextId in roadList[curId]) {
            if (visited[nextId]) continue
            visited[nextId] = true
            minDistance[nextId] = minDistance[curId] + 1
            queue.add(nextId)
        }
    }

    val sb = StringBuilder()
    for((idx, distance) in minDistance.withIndex()) {
        if(distance == targetDistance) {
            sb.appendLine(idx)
        }
    }
    if(sb.isEmpty()) sb.appendLine(-1)
    println(sb)
}