package 백준.Platinum.P4.p3176_도로네트워크

import kotlin.math.abs

const val EMPTY = -1
fun main() {
    val N = readln().toInt()
    val outLists = List(N) { mutableListOf<Pair<Int, Int>>() }
    repeat(N - 1) {
        val (A, B, C) = readln().split(" ").map(String::toInt).let { Triple(it[0] - 1, it[1] - 1, it[2]) }
        outLists[A].add(B to C)
        outLists[B].add(A to C)
    }
    val size = N.toString(2).length
    val root = 0
    val max = Array(size) { IntArray(N) { Int.MIN_VALUE } }
    val min = Array(size) { IntArray(N) { Int.MAX_VALUE } }
    val parentArr = Array(size) { IntArray(N) { EMPTY }.apply { this[root] = root } }
    val depth = IntArray(N)
    fun dfs(parent: Int) {
        for ((child, weight) in outLists[parent]) {
            if (parentArr.first()[child] != EMPTY) continue
            max.first()[child] = weight
            min.first()[child] = weight
            parentArr.first()[child] = parent
            depth[child] = depth[parent] + 1
            dfs(child)
        }
    }
    dfs(root)
    for (i in 1 until size) {
        for (j in 0 until N) {
            parentArr[i][j] = parentArr[i - 1][parentArr[i - 1][j]]
            max[i][j] = maxOf(max[i - 1][j], max[i - 1][parentArr[i - 1][j]])
            min[i][j] = minOf(min[i - 1][j], min[i - 1][parentArr[i - 1][j]])
        }
    }

    val sb = StringBuilder()
    val K = readln().toInt()
    fun depthDiff(u: Int, v: Int) = abs(depth[u] - depth[v])
    repeat(K) {
        var (D, E) = readln().split(" ").map { it.toInt() - 1 }
            .let { if (depth[it[0]] > depth[it[1]]) it[1] to it[0] else it[0] to it[1] }
        var curMax = Int.MIN_VALUE
        var curMin = Int.MAX_VALUE
        if (depth[D] != depth[E]) {
            val diff = depthDiff(D, E)
            for (i in 0 until size) {
                if ((diff and (1 shl i)) != 0) {
                    curMax = maxOf(curMax, max[i][E])
                    curMin = minOf(curMin, min[i][E])
                    E = parentArr[i][E]
                }
            }
        }

        for (i in (size - 1) downTo 0) {
            if (parentArr[i][D] != parentArr[i][E]) {
                curMax = maxOf(curMax, max[i][D], max[i][E])
                curMin = minOf(curMin, min[i][D], min[i][E])
                D = parentArr[i][D]
                E = parentArr[i][E]
            }
        }
        if (D != E) {
            curMax = maxOf(curMax, max[0][D], max[0][E])
            curMin = minOf(curMin, min[0][D], min[0][E])
        }
        sb.appendLine("$curMin $curMax")
    }
    println(sb)
}