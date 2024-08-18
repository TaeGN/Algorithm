package 백준.Platinum.P4.p3977_축구전술

import kotlin.math.min

const val MAX_N = 100000
const val EMPTY = 0

fun main() {
    val stack = ArrayDeque<Int>()
    val possibleSccIdArr = BooleanArray(MAX_N)
    val sccIdArr = IntArray(MAX_N)
    val parentArr = IntArray(MAX_N)
    val inLists = List(MAX_N) { mutableListOf<Int>() }
    val outLists = List(MAX_N) { mutableListOf<Int>() }
    var sccId = 0
    var id = 0
    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = min(parent, dfs(to))
            else if (sccIdArr[to] == EMPTY) parent = min(parent, parentArr[to])
        }

        if (parent == parentArr[from]) {
            ++sccId
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccId
                if (idx == from) break
            }
        }
        return parent
    }

    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) { t ->
        if (t > 0) readln()
        val (N, M) = readln().split(" ").map(String::toInt)
        sccId = 0
        id = 0
        stack.clear()
        for (i in 0 until N) {
            sccIdArr[i] = EMPTY
            parentArr[i] = EMPTY
            inLists[i].clear()
            outLists[i].clear()
        }
        repeat(M) {
            val (A, B) = readln().split(" ").map(String::toInt)
            outLists[A].add(B)
            inLists[B].add(A)
        }
        repeat(N) { idx -> if (sccIdArr[idx] == EMPTY) dfs(idx) }
        possibleSccIdArr.fill(true, 1, sccId + 1)
        repeat(N) { to ->
            if (inLists[to].any { from -> sccIdArr[from] != sccIdArr[to] }) possibleSccIdArr[sccIdArr[to]] = false
        }

        if (possibleSccIdArr.take(sccId + 1).count { it } == 1) {
            val targetId = possibleSccIdArr.indexOf(true)
            for (i in 0 until N) {
                if (sccIdArr[i] == targetId) sb.appendLine(i)
            }
        } else sb.appendLine("Confused")
        sb.appendLine()
    }
    println(sb)
}