package 백준.Platinum.P3.p16903_수열과쿼리20

class Trie(val idx: Int = 0) {
    val children = Array<Trie?>(2) { null }
    var count = 0
    fun add(str: String) {
        if (str.length == idx) {
            count++
            return
        }
        if (children[str[idx].digitToInt()] == null) children[str[idx].digitToInt()] = Trie(idx + 1)
        children[str[idx].digitToInt()]!!.add(str)
    }

    fun remove(str: String) {
        if (str.length == idx) {
            count--
            return
        }
        children[str[idx].digitToInt()]!!.remove(str)
        if (children[str[idx].digitToInt()]!!.let { child -> child.count == 0 && child.children.all { it == null } }) {
            children[str[idx].digitToInt()] = null
        }
    }

    fun maxValue(str: String): String {
        if (str.length == idx) return ""
        if (children[(str[idx].digitToInt() + 1) % 2] != null) {
            val nValue = children[(str[idx].digitToInt() + 1) % 2]!!.maxValue(str)
            if (nValue != "-1") return "1$nValue"
        }
        if (children[str[idx].digitToInt()] != null) {
            val nValue = children[str[idx].digitToInt()]!!.maxValue(str)
            if (nValue != "-1") return "0$nValue"
        }
        return "-1"
    }
}

fun main() {
    fun Int.toBinaryString() = toString(2).let { "0".repeat(30 - it.length) + it }
    val trie = Trie()
    trie.add(0.toBinaryString())
    val M = readln().toInt()
    val sb = StringBuilder()
    repeat(M) {
        val (type, x) = readln().trim().split(" ").map(String::toInt)
        when (type) {
            1 -> trie.add(x.toBinaryString())
            2 -> trie.remove(x.toBinaryString())
            3 -> sb.appendLine(trie.maxValue(x.toBinaryString()).toInt(2))
        }
    }
    println(sb)
}