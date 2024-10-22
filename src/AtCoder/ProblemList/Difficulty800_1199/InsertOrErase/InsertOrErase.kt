package AtCoder.ProblemList.Difficulty800_1199.InsertOrErase

data class Node(val value: Int = 0, var prev: Node? = null, var next: Node? = null) {
    fun add(node: Node) {
        node.prev = this
        node.next = next
        next?.prev = node
        next = node
    }

    fun remove() {
        prev?.next = next
        next?.prev = prev
    }

    override fun toString(): String {
        var node: Node? = this
        val sb = StringBuilder()
        while (node != null) {
            sb.append("${node.value} ")
            node = node.next
        }
        return sb.toString()
    }
}

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val Q = readln().trim().toInt()
    val root = Node()
    var prevNode = root
    val nodeMap = mutableMapOf<Int, Node>()
    for (a in A) {
        val curNode = Node(a)
        prevNode.add(curNode)
        nodeMap[a] = curNode
        prevNode = curNode
    }
    repeat(Q) {
        val query = readln().trim().split(" ").map(String::toInt)
        if (query[0] == 1) {
            val newNode = Node(query[2])
            nodeMap[query[1]]!!.add(newNode)
            nodeMap[query[2]] = newNode
        } else {
            nodeMap[query[1]]!!.remove()
        }
    }
    println(root.next)
}