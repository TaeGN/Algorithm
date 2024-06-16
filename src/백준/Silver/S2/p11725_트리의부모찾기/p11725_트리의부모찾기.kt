package 백준.Silver.S2.p11725_트리의부모찾기

import java.io.StreamTokenizer

const val EMPTY = 0
const val ROOT = 1
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val nodeList = List(n + 1) { mutableListOf<Int>() }
    repeat(n - 1) {
        val nodeA = readInt()
        val nodeB = readInt()
        nodeList[nodeA].add(nodeB)
        nodeList[nodeB].add(nodeA)
    }

    val parentArr = IntArray(n + 1)
    fun setParent(node: Int) {
        for(nextNode in nodeList[node]) {
            if(parentArr[nextNode] != EMPTY) continue
            parentArr[nextNode] = node
            setParent(nextNode)
        }
    }

    setParent(ROOT)
    val sb = StringBuilder()
    parentArr.asSequence().filterIndexed { index, _ -> index >= 2 }.forEach(sb::appendLine)
    println(sb)
}