package 백준.Platinum.P4.p2207_가위바위보

import java.io.StreamTokenizer

const val EMPTY = -1

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val outLists = List(2 * M + 1) { mutableSetOf<Int>() }
    fun Int.not() = if (this > M) this - M else this + M
    repeat(N) {
        val i = nextInt().let { if (it > 0) it else -it + M }
        val j = nextInt().let { if (it > 0) it else -it + M }
        outLists[i.not()].add(j)
        outLists[j.not()].add(i)
    }

    val stack = ArrayDeque<Int>()
    val sccIdArr = IntArray(2 * M + 1) { EMPTY }
    val parentArr = IntArray(2 * M + 1) { EMPTY }
    var id = 0
    var sccId = 0
    var isPossible = true
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
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

    for (i in 1..2 * M) {
        if (sccIdArr[i] == EMPTY) scc(i)
        if (!isPossible) break
    }
    println(if (isPossible) "^_^" else "OTL")
}