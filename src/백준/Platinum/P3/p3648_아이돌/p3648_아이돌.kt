package 백준.Platinum.P3.p3648_아이돌

import java.util.StringTokenizer

const val EMPTY = -1
const val MAX_N = 1000

fun main() = with(System.`in`.bufferedReader()) {
    fun Int.not() = if (this > MAX_N) this - MAX_N else this + MAX_N
    val stack = ArrayDeque<Int>()
    val outLists = List(2 * MAX_N + 1) { mutableListOf<Int>() }
    val sccIdArr = IntArray(2 * MAX_N + 1) { EMPTY }
    val parentArr = IntArray(2 * MAX_N + 1) { EMPTY }
    var sccId = 0
    var id = 0
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

    fun init(N: Int) {
        sccId = 0
        id = 0
        isPossible = true
        stack.clear()
        for (i in 1..N) {
            outLists[i].clear()
            outLists[i.not()].clear()
            sccIdArr[i] = EMPTY
            sccIdArr[i.not()] = EMPTY
            parentArr[i] = EMPTY
            parentArr[i.not()] = EMPTY
        }
    }

    val sb = StringBuilder()
    var st = StringTokenizer(readLine())
    while (true) {
        try {
            val N = st.nextToken().toInt()
            val M = st.nextToken().toInt()
            init(N)
            repeat(M) {
                st = StringTokenizer(readLine())
                val a = st.nextToken().toInt().let { if (it > 0) it else -it + MAX_N }
                val b = st.nextToken().toInt().let { if (it > 0) it else -it + MAX_N }
                outLists[a.not()].add(b)
                outLists[b.not()].add(a)
            }
            outLists[1.not()].add(1)
            for (i in 1..2 * N) {
                if (sccIdArr[i] == EMPTY) scc(i)
                if (!isPossible) break
            }
            if (isPossible) sb.appendLine("yes")
            else sb.appendLine("no")
            st = StringTokenizer(readLine())
        } catch (e: Exception) {
            println(sb)
            break
        }
    }
}