package 백준.Gold.G3.p16934_게임닉네임

class Trie(val idx: Int) {
    private val children = Array<Trie?>(26) { null }
    private var count = 0
    fun add(str: String, isFinished: Boolean = false): String {
        if (str.length == idx) return if (++count == 1 || isFinished) "" else "$count"
        if (children[str[idx] - 'a'] == null) {
            children[str[idx] - 'a'] = Trie(idx + 1)
            return (if (isFinished) "" else "${str[idx]}") + children[str[idx] - 'a']!!.add(str, true)
        }
        return (if (isFinished) "" else "${str[idx]}") + children[str[idx] - 'a']!!.add(str, isFinished)
    }
}

fun main() {
    val N = readln().toInt()
    val trie = Trie(0)
    val sb = StringBuilder()
    repeat(N) { sb.appendLine(trie.add(readln())) }
    println(sb)
}