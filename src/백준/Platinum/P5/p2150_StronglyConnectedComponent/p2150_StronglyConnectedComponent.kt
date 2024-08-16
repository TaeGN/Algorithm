package 백준.Platinum.P5.p2150_StronglyConnectedComponent

import java.io.StreamTokenizer
import kotlin.math.min

const val EMPTY = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val V = nextInt()
    val E = nextInt()
    val lineLists = List(V + 1) { mutableListOf<Int>() }
    repeat(E) { lineLists[nextInt()].add(nextInt()) }

    val sccLists = mutableListOf<MutableList<Int>>()
    val parentArr = IntArray(V + 1)
    val stack = ArrayDeque<Int>()
    val visitedArr = BooleanArray(V + 1)
    var id = 0
    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in lineLists[from]) {
            if (parentArr[to] == EMPTY) parent = min(parent, dfs(to))
            else if (!visitedArr[to]) parent = min(parent, parentArr[to])
        }

        if (parent == parentArr[from]) {
            val list = mutableListOf<Int>()
            while (true) {
                val idx = stack.removeFirst()
                list.add(idx)
                visitedArr[idx] = true
                if (idx == from) break
            }
            sccLists.add(list)
        }
        return parent
    }

    for (i in 1..V) {
        if (!visitedArr[i]) dfs(i)
    }
    val sb = StringBuilder().appendLine(sccLists.size)
    sccLists.forEach { it.sort() }
    sccLists.sortBy { it[0] }
    for (list in sccLists) {
        sb.appendLine(list.joinToString(" ", "", " -1"))
    }
    println(sb)
}