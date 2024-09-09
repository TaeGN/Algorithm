package 백준.Platinum.P3.p17365_별다줄

const val MOD = 1_000_000_007

class Trie(val idx: Int) {
    val children = Array<Trie?>(26) { null }
    var count = 0
    fun add(str: String) {
        if (idx > 0) count++
        if (str.length == idx) return
        getChild(str[idx]).add(str)
    }

    private fun getChild(c: Char): Trie {
        if (children[c - 'a'] == null) children[c - 'a'] = Trie(idx + 1)
        return children[c - 'a']!!
    }
}

fun main() {
    val root = Trie(0)
    repeat(readln().toInt()) { root.add(readln()) }
    val S = readln()
    val dp = IntArray(S.length)
    val countMatrix = Array(S.length) { IntArray(301) }
    fun Trie.count(str: String, startIdx: Int) {
        countMatrix[startIdx][idx] = count
        if (startIdx + idx == str.length) return
        if (children[str[startIdx + idx] - 'a'] != null) children[str[startIdx + idx] - 'a']!!.count(str, startIdx)
    }
    for (i in S.indices) {
        root.count(S, i)
    }
    for (i in S.length - 1 downTo 0) {
        dp[i] = countMatrix[i].getOrElse(S.length - i) { 0 }
        for (j in 1..minOf(300, (S.length - i - 1))) {
            dp[i] = ((dp[i] + countMatrix[i][j].toLong() * dp[i + j] % MOD) % MOD).toInt()
        }
    }
    println(dp[0])
}