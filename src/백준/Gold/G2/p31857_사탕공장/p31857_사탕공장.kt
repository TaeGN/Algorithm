package 백준.Gold.G2.p31857_사탕공장

class Node<T>(val value: T? = null) {
    var prev: Node<T>? = null
    var next: Node<T>? = null

    fun connect(other: Node<T>): Node<T> {
        this.next = other
        other.prev = this
        return other
    }
}

class LinkedList<T> {
    private val head = Node<T>()
    private val tail = Node<T>()
    private var rthNode: Node<T> = head
    private var r: Int = 0
    private var size: Int = 0

    init {
        head.connect(tail)
    }

    fun moveLeft() {
        if (size <= 1) return
        rthNode = if (r == size) head.next!! else rthNode.next!!
        val first = head.next!!
        remove(first)
        add(first)
    }

    fun moveRight() {
        if (size <= 1) return
        rthNode = if (r == 1) tail.prev!! else rthNode.prev!!
        val last = tail.prev!!
        remove(last)
        addFirst(last)
    }

    private fun remove(node: Node<T>) {
        val prev = node.prev!!
        val next = node.next!!
        prev.connect(next)
        size--
    }

    fun add(value: T) = add(Node(value))
    private fun add(node: Node<T>) {
        val last = tail.prev!!
        last.connect(node).connect(tail)
        size++
    }

    private fun addFirst(node: Node<T>) {
        val second = head.next!!
        head.connect(node).connect(second)
        size++
    }

    fun exchange(other: LinkedList<T>, toIndex: Int) {
        this.setRthNode(toIndex)
        other.setRthNode(toIndex)
        val thisHead = head
        val thisFirst = head.next!!
        val thisRth = this.rthNode
        val thisRthNext = thisRth.next!!
        val otherHead = other.head
        val otherFirst = other.head.next!!
        val otherRth = other.rthNode
        val otherRthNext = otherRth.next!!
        thisHead.connect(otherFirst)
        otherRth.connect(thisRthNext)
        otherHead.connect(thisFirst)
        thisRth.connect(otherRthNext)
        this.rthNode = otherRth
        other.rthNode = thisRth
    }

    fun setRthNode(r: Int) {
        while (this.r > r) {
            this.r--
            rthNode = rthNode.prev!!
        }
        while (this.r < r) {
            this.r++
            rthNode = rthNode.next!!
        }
    }

    override fun toString(): String {
        var node = head.next!!
        val sb = StringBuilder()
        while (node.value != null) {
            sb.append(node.value)
            node = node.next!!
        }
        return sb.toString()
    }
}


fun main() = with(System.`in`.bufferedReader()) {
    val (N, R, Q) = readLine().split(" ").map(String::toInt).let { Triple(it[0], it[1], it[2]) }
    fun String.toLinkedList(): LinkedList<Char> {
        val list = LinkedList<Char>()
        for (c in this) {
            list.add(c)
        }
        list.setRthNode(R)
        return list
    }

    val list1 = readLine().toLinkedList()
    val list2 = readLine().toLinkedList()
    var curR = R
    repeat(Q) {
        val input = readLine().split(" ")
        when (input[0]) {
            "S" -> list1.exchange(list2, curR)
            "L" -> (if (input[1] == "1") list1 else list2).moveLeft()
            "R" -> (if (input[1] == "1") list1 else list2).moveRight()
            "I" -> curR++
            "D" -> curR--
        }
    }
    println("$list1\n$list2")
}