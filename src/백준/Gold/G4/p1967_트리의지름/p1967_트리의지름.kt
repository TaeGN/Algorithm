package 백준.Gold.G4.p1967_트리의지름

import java.io.StreamTokenizer
import kotlin.math.max

data class Node(val id: Int, var weight: Int = Int.MAX_VALUE, val children: MutableList<Node> = mutableListOf()) {
    fun addChild(child: Node) = children.add(child)
}

const val ROOT_ID = 1

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val nodeList = MutableList(n + 1) { id -> Node(id) }
    val root = nodeList[ROOT_ID]
    repeat(n - 1) {
        val parentId = readInt()
        val childId = readInt()
        val weight = readInt()
        nodeList[childId].weight = weight
        nodeList[parentId].addChild(nodeList[childId])
    }

    val maxWeightList = IntArray(n + 1) { -1 }
    fun maxWeight(node: Node): Int {
        var maxWeight = 0
        for (child in node.children) {
            if (maxWeightList[child.id] == -1) maxWeightList[child.id] = maxWeight(child)
            maxWeight = max(maxWeight, maxWeightList[child.id] + child.weight)
        }
        return maxWeight
    }
    maxWeightList[root.id] = maxWeight(root)
    nodeList.maxOfOrNull { node ->
        val weightList = node.children.map { child -> maxWeightList[child.id] + child.weight }.sortedDescending()
        when (weightList.size) {
            0 -> 0
            1 -> weightList[0]
            else -> weightList[0] + weightList[1]
        }
    }.let(::println)
}