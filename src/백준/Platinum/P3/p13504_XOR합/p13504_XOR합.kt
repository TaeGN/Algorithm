package 백준.Platinum.P3.p13504_XOR합

class Trie(val idx: Int) {
    val children = Array<Trie?>(2) { null }
    fun add(str: String) {
        if (str.length == idx) return
        getChild(str[idx]).add(str)
    }

    private fun getChild(c: Char): Trie {
        if (children[c.digitToInt()] == null) children[c.digitToInt()] = Trie(idx + 1)
        return children[c.digitToInt()]!!
    }

    fun result(str: String): String? {
        if (str.length == idx) return ""
        val curBit = str[idx].digitToInt()
        if (children[(curBit + 1) % 2] != null) children[(curBit + 1) % 2]!!.result(str)?.let { return "1$it" }
        if (children[curBit] != null) children[curBit]!!.result(str)?.let { return "0$it" }
        return null
    }
}

fun main() {
    fun Int.toBinaryString() = toString(2).let { "${"0".repeat(32 - it.length)}$it" }
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        readln()
        val trie = Trie(0)
        var result = 0
        var sum = 0
        for (elm in readln().trim().split(" ").map(String::toInt)) {
            sum = sum xor elm
            val str = sum.toBinaryString()
            result = maxOf(result, sum, trie.result(str)?.toInt(2) ?: 0)
            trie.add(str)
        }
        sb.appendLine(result)
    }
    println(sb)
}