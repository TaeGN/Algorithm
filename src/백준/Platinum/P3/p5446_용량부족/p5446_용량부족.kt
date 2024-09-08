package 백준.Platinum.P3.p5446_용량부족

class Trie(val idx: Int) {
    private val children = mutableMapOf<Char, Trie>()
    private var hasDeleteFile = false
    private var needToDelete = false
    private var canDelete = true
    fun addDeleteFile(str: String) {
        hasDeleteFile = true
        if (str.length == idx) {
            needToDelete = true
            return
        }
        getChild(str[idx]).addDeleteFile(str)
    }

    fun addCanNotDeleteFile(str: String) {
        canDelete = false
        if (str.length == idx) return
        getChild(str[idx]).addCanNotDeleteFile(str)
    }

    private fun getChild(c: Char): Trie {
        if (c !in children) children[c] = Trie(idx + 1)
        return children[c]!!
    }

    fun count(): Int {
        if (!hasDeleteFile) return 0
        if (canDelete) return 1
        var count = if (needToDelete) 1 else 0
        for ((_, child) in children) {
            count += child.count()
        }
        return count
    }
}

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val trie = Trie(0)
        repeat(readln().toInt()) { trie.addDeleteFile(readln()) }
        repeat(readln().toInt()) { trie.addCanNotDeleteFile(readln()) }
        sb.appendLine(trie.count())
    }
    println(sb)
}