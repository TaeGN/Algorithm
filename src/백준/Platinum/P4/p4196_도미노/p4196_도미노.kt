package 백준.Platinum.P4.p4196_도미노

import java.io.StreamTokenizer
import kotlin.math.min

const val MAX_N = 100001
const val EMPTY = 0

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    var id = 0
    val sccSets = mutableListOf<Set<Int>>()
    val stack = ArrayDeque<Int>()
    val parentArr = IntArray(MAX_N)
    val finishArr = BooleanArray(MAX_N)
    val inLists = List(MAX_N) { mutableListOf<Int>() }
    val outLists = List(MAX_N) { mutableListOf<Int>() }
    val sb = StringBuilder()
    val T = nextInt()

    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = min(parent, dfs(to))
            else if (!finishArr[to]) parent = min(parent, parentArr[to])
        }

        if (parent == parentArr[from]) {
            val set = mutableSetOf<Int>()
            while (true) {
                val idx = stack.removeFirst()
                finishArr[idx] = true
                set.add(idx)
                if (idx == from) break
            }
            sccSets.add(set)
        }
        return parent
    }
    repeat(T) {
        val N = nextInt()
        val M = nextInt()
        sccSets.clear()
        stack.clear()
        id = 0
        for (i in 1..N) {
            parentArr[i] = 0
            finishArr[i] = false
            inLists[i].clear()
            outLists[i].clear()
        }
        repeat(M) {
            val from = nextInt()
            val to = nextInt()
            inLists[to].add(from)
            outLists[from].add(to)
        }

        for (i in 1..N) {
            if (!finishArr[i]) dfs(i)
        }

        var result = 0
        a@ for (set in sccSets) {
            for (to in set) {
                for (from in inLists[to]) {
                    if (from !in set) continue@a
                }
            }
            result++
        }

        sb.appendLine(result)
    }
    println(sb)
}