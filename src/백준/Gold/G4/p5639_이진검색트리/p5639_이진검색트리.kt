package 백준.Gold.G4.p5639_이진검색트리

data class Node(val key: Int, var leftChild: Node? = null, var rightChild: Node? = null) {
    init {
        require(key > 0)
    }
}

class BinaryTree() {
    private val root = Node(Int.MAX_VALUE)

    fun add(node: Node) {
        val parent = findParent(root, node)
        if (parent.key > node.key) parent.leftChild = node
        else parent.rightChild = node
    }

    private fun findParent(parent: Node, child: Node): Node = if (parent.key > child.key) {
        if (parent.leftChild == null) parent
        else findParent(parent.leftChild!!, child)
    } else {
        if (parent.rightChild == null) parent
        else findParent(parent.rightChild!!, child)
    }

    fun printPostorderTraversal() {
        val sb = StringBuilder()
        fun printPostorderTraversal(node: Node) {
            node.leftChild?.let { printPostorderTraversal(it) }
            node.rightChild?.let { printPostorderTraversal(it) }
            sb.appendLine(node.key)
        }
        root.leftChild?.let { printPostorderTraversal(it) }
        println(sb)
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val binaryTree = BinaryTree()
    do {
        val input = br.readLine() ?: break
        binaryTree.add(Node(input.toInt()))
    } while (true)

    binaryTree.printPostorderTraversal()
}