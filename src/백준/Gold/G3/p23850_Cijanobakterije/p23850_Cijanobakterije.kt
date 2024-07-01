package 백준.Gold.G3.p23850_Cijanobakterije

import java.io.StreamTokenizer
import kotlin.math.max

data class Node(val id: Int, val children: MutableSet<Node> = mutableSetOf()) {
    private var maxDepth = 0

    fun addChild(child: Node) {
        children.add(child)
    }

    fun maxChainLength(): Int {
        var first = 0
        var second = 0
        for (child in children) {
            val childMaxDepth = child.maxDepth()
            if (first < childMaxDepth) {
                second = first
                first = childMaxDepth
            } else if (second < childMaxDepth) {
                second = childMaxDepth
            }
        }
        return 1 + first + second
    }

    private fun maxDepth(): Int = if (maxDepth > 0) maxDepth
    else 1 + (if (children.isNotEmpty()) children.maxOf { it.maxDepth() } else 0).also { maxDepth = it }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun IntArray.find(id: Int): Int = if (this[id] == id) id else find(this[id]).also { this[id] = it }
    fun IntArray.union(id1: Int, id2: Int) {
        this[find(id1)] = find(id2)
    }

    val n = readInt()
    val m = readInt()
    val nodeList = List(n) { Node(it) }
    val parentArr = IntArray(n) { it }
    repeat(m) {
        val id1 = readInt() - 1
        val id2 = readInt() - 1
        nodeList[id1].addChild(nodeList[id2])
        parentArr.union(id1, id2)
    }

    val lengthMap = mutableMapOf<Int, Int>()
    for (id in 0 until n) {
        val groupId = parentArr.find(id)
        lengthMap[groupId] = max(lengthMap.getOrDefault(groupId, 0), nodeList[id].maxChainLength())
    }

    println(lengthMap.values.sum())
}