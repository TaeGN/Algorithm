package 백준.Gold.G5.p15681_트리와쿼리

import java.io.StreamTokenizer

data class Node(val children: MutableList<Node> = mutableListOf()) {
    private var subNodesCount = 1
    fun subNodesCount(): Int = if (subNodesCount > 1) subNodesCount
    else (1 + children.sumOf { it.subNodesCount() }).also { subNodesCount = it }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val R = readInt()
    val Q = readInt()

    val lineList = List(N + 1) { mutableListOf<Int>() }
    repeat(N - 1) {
        val U = readInt()
        val V = readInt()
        lineList[U].add(V)
        lineList[V].add(U)
    }

    val nodeList = List(N + 1) { Node() }
    val queue = ArrayDeque<Int>()
    val visited = BooleanArray(N + 1)
    visited[R] = true
    queue.add(R)

    while (queue.isNotEmpty()) {
        val parent = queue.removeFirst()
        for (child in lineList[parent]) {
            if (visited[child]) continue
            visited[child] = true
            queue.add(child)
            nodeList[parent].children.add(nodeList[child])
        }
    }

    val sb = StringBuilder()
    repeat(Q) {
        sb.appendLine(nodeList[readInt()].subNodesCount())
    }
    println(sb)
}