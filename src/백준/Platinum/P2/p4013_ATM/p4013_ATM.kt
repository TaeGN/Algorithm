package 백준.Platinum.P2.p4013_ATM

import java.io.StreamTokenizer

const val EMPTY = -1

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val outLists = List(N + 1) { mutableListOf<Int>() }
    repeat(M) { outLists[nextInt()].add(nextInt()) }

    val sccSetLists = mutableListOf<Set<Int>>()
    val sccIdArr = IntArray(N + 1) { EMPTY }
    val parentArr = IntArray(N + 1) { EMPTY }
    val stack = ArrayDeque<Int>()
    var id = 0
    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, dfs(to))
            else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
        }

        if (parent == parentArr[from]) {
            val sccSet = mutableSetOf<Int>()
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccSetLists.size
                sccSet.add(idx)
                if (idx == from) break
            }
            sccSetLists.add(sccSet)
        }
        return parent
    }

    val priceArr = IntArray(N + 1) { idx -> if (idx == 0) 0 else nextInt() }
    val S = nextInt()
    val P = nextInt()
    dfs(S)
    val sccSetsPrice = sccSetLists.map { sccSet -> sccSet.sumOf { priceArr[it] } }
    val startSccId = sccIdArr[S]
    val dp = IntArray(sccSetLists.size).apply { this[startSccId] = sccSetsPrice[startSccId] }
    for (fromSccId in sccSetLists.size - 1 downTo 0) {
        for (from in sccSetLists[fromSccId]) {
            for (to in outLists[from]) {
                val toSccId = sccIdArr[to]
                if (fromSccId == toSccId) continue
                dp[toSccId] = maxOf(dp[toSccId], dp[fromSccId] + sccSetsPrice[toSccId])
            }
        }
    }

    val pArr = IntArray(P) { nextInt() }
    val hasPBySccSets = BooleanArray(sccSetLists.size)
    for (p in pArr) {
        if (sccIdArr[p] == EMPTY) continue
        hasPBySccSets[sccIdArr[p]] = true
    }

    var result = 0
    for (i in dp.indices) {
        if (hasPBySccSets[i]) result = maxOf(result, dp[i])
    }
    println(result)
}