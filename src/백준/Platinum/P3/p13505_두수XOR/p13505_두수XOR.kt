package 백준.Platinum.P3.p13505_두수XOR

class Trie(val idx: Int = 0) {
    private val children = Array<Trie?>(2) { null }
    fun add(str: String) {
        if (idx == str.length) return
        if (children[str[idx].digitToInt()] == null) children[str[idx].digitToInt()] = Trie(idx + 1)
        children[str[idx].digitToInt()]!!.add(str)
    }

    fun find(str: String): String {
        if (str.length == idx) return ""
        return if (children[(str[idx].digitToInt() + 1) % 2] != null) {
            "${(str[idx].digitToInt() + 1) % 2}${children[(str[idx].digitToInt() + 1) % 2]!!.find(str)}"
        } else "${str[idx].digitToInt()}${children[str[idx].digitToInt()]!!.find(str)}"
    }
}

fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toInt)
        .map { num -> num.toString(2).let { "0".repeat(30 - it.length) + it } }
    val trie = Trie()
    aArr.forEach { trie.add(it) }
    println(aArr.maxOf { it.toInt(2) xor trie.find(it).toInt(2) })
}