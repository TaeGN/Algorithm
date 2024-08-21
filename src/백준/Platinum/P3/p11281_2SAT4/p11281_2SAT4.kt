package 백준.Platinum.P3.p11281_2SAT4

import java.io.StreamTokenizer

const val EMPTY = -1

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val outLists = List(2 * N + 1) { mutableListOf<Int>() }
    fun Int.not() = if (this > N) this - N else this + N
    repeat(M) {
        val i = nextInt().let { if (it > 0) it else -it + N }
        val j = nextInt().let { if (it > 0) it else -it + N }
        outLists[i.not()].add(j)
        outLists[j.not()].add(i)
    }

    val stack = ArrayDeque<Int>()
    val parentArr = IntArray(2 * N + 1) { EMPTY }
    val sccIdArr = IntArray(2 * N + 1) { EMPTY }
    var id = 0
    var sccId = 0
    var isPossible = true
    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, dfs(to))
            else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
        }
        if (parent == parentArr[from]) {
            sccId++
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccId
                if (sccIdArr[idx.not()] == sccId) isPossible = false
                if (idx == from) break
            }
        }
        return parent
    }

    for (i in 1..2 * N) {
        if (sccIdArr[i] == EMPTY) dfs(i)
        if (!isPossible) break
    }

    fun result(): String {
        if (!isPossible) return "0"
        val sb = StringBuilder().appendLine(1)
        for (i in 1..N) {
            if (sccIdArr[i] > sccIdArr[i.not()]) sb.append("0 ")
            else sb.append("1 ")
        }
        return sb.toString()
    }

    println(result())
}