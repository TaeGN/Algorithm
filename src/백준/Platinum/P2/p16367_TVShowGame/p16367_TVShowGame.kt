package 백준.Platinum.P2.p16367_TVShowGame

import java.util.StringTokenizer

const val EMPTY = -1

fun main() {
    val (k, n) = readln().split(" ").map(String::toInt)
    val outSetList = List(2 * k + 1) { mutableSetOf<Int>() }
    fun Int.not() = if (this > k) this - k else this + k

    repeat(n) {
        val st = StringTokenizer(readln())
        val a = st.nextToken().toInt().let { if (st.nextToken() == "R") it else it + k }
        val b = st.nextToken().toInt().let { if (st.nextToken() == "R") it else it + k }
        val c = st.nextToken().toInt().let { if (st.nextToken() == "R") it else it + k }
        outSetList[a.not()].add(b)
        outSetList[b.not()].add(a)
        outSetList[b.not()].add(c)
        outSetList[c.not()].add(b)
        outSetList[c.not()].add(a)
        outSetList[a.not()].add(c)
    }
    val stack = ArrayDeque<Int>()
    val parentArr = IntArray(2 * k + 1) { EMPTY }
    val sccIdArr = IntArray(2 * k + 1) { EMPTY }
    var sccId = 0
    var id = 0
    var isPossible = true
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outSetList[from]) {
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

    for (i in 1..2 * k) {
        if (sccIdArr[i] == EMPTY) scc(i)
        if (!isPossible) break
    }

    fun result(): String {
        if (!isPossible) return "-1"
        val sb = StringBuilder()
        for (i in 1..k) {
            if (sccIdArr[i] < sccIdArr[i.not()]) sb.append("R")
            else sb.append("B")
        }
        return sb.toString()
    }
    println(result())
}