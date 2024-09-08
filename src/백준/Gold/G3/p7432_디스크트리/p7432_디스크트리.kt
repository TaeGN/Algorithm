package 백준.Gold.G3.p7432_디스크트리

import java.util.TreeMap

class Trie(val idx: Int) {
    private val children = TreeMap<String, Trie>()
    fun add(strArr: List<String>) {
        if (strArr.size == idx) return
        if (strArr[idx] !in children) children[strArr[idx]] = Trie(idx + 1)
        children[strArr[idx]]!!.add(strArr)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for ((name, child) in children) {
            sb.append("${" ".repeat(idx)}$name\n$child")
        }
        return sb.toString()
    }
}

fun main() {
    val N = readln().toInt()
    val trie = Trie(0)
    repeat(N) { trie.add(readln().split("\\")) }
    println(trie)
}