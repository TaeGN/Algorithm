package 백준.Gold.G4.p9935_문자열폭발

data class Node<T>(val value: T, var prev: Node<T>? = null, var next: Node<T>? = null) {
    fun prev(count: Int = 1): Node<T>? {
        var node: Node<T>? = this
        for (i in 0 until count) {
            node = node?.prev
        }
        return node
    }
}

data class Word(val word: String) {
    private val root = Node(' ')

    init {
        require(word.isNotEmpty())
        var node = root
        for (element in word) {
            val newNode = Node(element)
            newNode.prev = node
            node.next = newNode
            node = newNode
        }
    }

    fun bomb(bomb: String) {
        val kmpPattern = kmpPattern(bomb)
        var curNode: Node<Char>? = root.next
        var j = 0
        while (curNode != null) {
            while (j > 0 && curNode.value != bomb[j]) j = kmpPattern[j - 1]
            if (curNode.value == bomb[j]) {
                if (++j == kmpPattern.size) {
                    val prevNode = curNode.prev(kmpPattern.size)
                    prevNode?.next = curNode.next
                    curNode.next?.prev = prevNode
                    curNode = curNode.prev(kmpPattern.size * 2 - 1) ?: root
                    j = 0
                }
            }
            curNode = curNode.next
        }
    }

    private fun kmpPattern(bomb: String): List<Int> {
        val kmpPattern = IntArray(bomb.length)
        var j = 0
        for (i in 1 until bomb.length) {
            while (j > 0 && bomb[i] != bomb[j]) j = kmpPattern[j - 1]
            if (bomb[i] == bomb[j]) kmpPattern[i] = ++j
        }
        return kmpPattern.toList()
    }

    override fun toString(): String {
        var node: Node<Char>? = root.next ?: return "FRULA"
        val sb = StringBuilder()
        while (node != null) {
            sb.append("${node.value}")
            node = node.next
        }
        return sb.toString()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val word = br.readLine()
    val bomb = br.readLine()
    val wordA = Word(word)
    wordA.bomb(bomb)
    println(wordA)
}