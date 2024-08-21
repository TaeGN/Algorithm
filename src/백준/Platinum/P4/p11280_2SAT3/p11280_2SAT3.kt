package 백준.Platinum.P4.p11280_2SAT3

import java.io.StreamTokenizer

const val EMPTY = 0

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

    val sccIdArr = IntArray(2 * N + 1)
    val finishArr = BooleanArray(2 * N + 1)
    val parentArr = IntArray(2 * N + 1)
    val stack = ArrayDeque<Int>()
    var id = 0
    var sccId = 0
    var isPossible = true
    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, dfs(to))
            else if (!finishArr[to]) parent = minOf(parent, parentArr[to])
        }
        if (parent == parentArr[from]) {
            sccId++
            while (true) {
                val idx = stack.removeFirst()
                finishArr[idx] = true
                sccIdArr[idx] = sccId
                if (sccIdArr[(if (idx > N) idx - N else idx + N)] == sccId) isPossible = false
                if (idx == from) break
            }
        }
        return parent
    }

    for (i in 1..2 * N) {
        if (!finishArr[i]) dfs(i)
        if (!isPossible) break
    }

    println(if (isPossible) 1 else 0)
}