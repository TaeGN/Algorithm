package AtCoder.ProblemList.Difficulty400_799.ManyReplacement

data class Node(val value: Int = -1, var next: Node? = null)

class LinkedList {
    private val head = Node()
    private var tail = head

    fun first() = head.next
    fun last() = if (isEmpty()) null else tail
    fun isEmpty() = head == tail
    fun add(node: Node) {
        tail.next = node
        tail = node
    }

    fun addAll(list: LinkedList) {
        if (list.isEmpty()) return
        tail.next = list.first()
        tail = list.last()!!
    }

    fun clear() {
        head.next = null
        tail = head
    }
}

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim()
    val lists = List(26) { LinkedList() }
    for (i in S.indices) {
        lists[S[i] - 'a'].add(Node(i))
    }
    repeat(readln().trim().toInt()) {
        val (C, D) = readln().trim().split(" ")
        if (C != D) {
            lists[D.first() - 'a'].addAll(lists[C.first() - 'a'])
            lists[C.first() - 'a'].clear()
        }
    }
    val result = CharArray(N)
    for (i in lists.indices) {
        var node = lists[i].first()
        while (node != null) {
            result[node.value] = 'a' + i
            node = node.next
        }
    }
    println(result.joinToString(""))
}
