package 백준.Silver.S1.p1991_트리순회

import java.io.StreamTokenizer

data class Node(val name: Char, var leftChild: Node? = null, var rightChild: Node? = null)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun readString(): String? {
        nextToken()
        return sval
    }

    val n = readInt()
    val nodeList = List(n) { idx -> Node('A' + idx) }
    repeat(n) {
        val nodeName = readString()
        val leftChildName = readString()
        val rightChildName = readString()
        val node = nodeList[nodeName!![0] - 'A']
        leftChildName?.let { node.leftChild = nodeList[it[0] - 'A'] }
        rightChildName?.let { node.rightChild = nodeList[it[0] - 'A'] }
    }

    fun Node.preorder(): StringBuilder {
        val sb = StringBuilder()
        sb.append(name)
        leftChild?.let { sb.append(it.preorder()) }
        rightChild?.let { sb.append(it.preorder()) }
        return sb
    }

    fun Node.inorder(): StringBuilder {
        val sb = StringBuilder()
        leftChild?.let { sb.append(it.inorder()) }
        sb.append(name)
        rightChild?.let { sb.append(it.inorder()) }
        return sb
    }

    fun Node.postorder(): StringBuilder {
        val sb = StringBuilder()
        leftChild?.let { sb.append(it.postorder()) }
        rightChild?.let { sb.append(it.postorder()) }
        sb.append(name)
        return sb
    }

    val root = nodeList[0]
    val sb = StringBuilder()
    sb.appendLine(root.preorder())
    sb.appendLine(root.inorder())
    sb.appendLine(root.postorder())
    println(sb)
}