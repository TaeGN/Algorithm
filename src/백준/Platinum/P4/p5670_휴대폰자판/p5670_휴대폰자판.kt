package 백준.Platinum.P4.p5670_휴대폰자판

class Trie(val idx: Int = 0, private val children: Array<Trie?> = Array(26) { null }, var isWord: Boolean = false) {
    fun add(str: String) {
        if (str.length == idx) {
            isWord = true
            return
        }
        if (children[str[idx] - 'a'] == null) children[str[idx] - 'a'] = Trie(idx + 1)
        children[str[idx] - 'a']!!.add(str)
    }

    fun count(str: String): Int = when (idx) {
        0 -> 1 + children[str[idx] - 'a']!!.count(str)
        str.length -> 0
        else -> (if (children.count { it != null } == 1 && !isWord) 0 else 1) + children[str[idx] - 'a']!!.count(str)
    }
}

fun main() {
    val sb = StringBuilder()
    try {
        while (true) {
            val N = readln().toInt()
            val trie = Trie()
            val strArr = Array(N) { readln() }
            strArr.forEach { trie.add(it) }
            val totalCount = strArr.sumOf { trie.count(it) }
            sb.appendLine(String.format("%.2f", (totalCount.toDouble() / N)))
        }
    } catch (e: RuntimeException) {
        println(sb)
    }
}