package AtCoder.ABC.ABC353.E.trie

class Trie(val idx: Int) {
    val children = Array<Trie?>(26) { null }
    var count = 0
    fun add(str: String): Long {
        if (str.length == idx) return (count++).toLong()
        return (if (idx > 0) count++ else 0) + getChild(str[idx]).add(str)
    }

    private fun getChild(c: Char): Trie {
        if (children[c - 'a'] == null) children[c - 'a'] = Trie(idx + 1)
        return children[c - 'a']!!
    }
}

fun main() {
    val N = readln().toInt()
    val sArr = readln().trim().split(" ")
    val trie = Trie(0)
    println(sArr.fold(0L) { acc, s -> acc + trie.add(s) })
}