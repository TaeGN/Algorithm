package 백준.Platinum.P3.p13511_트리와쿼리2

import kotlin.math.abs

const val EMPTY = -1
const val ROOT = 0
fun main() {
    val N = readln().toInt()
    val size = N.toString(2).length
    val outLists = List(N) { mutableListOf<Pair<Int, Int>>() }
    repeat(N - 1) {
        val (u, v, w) = readln().split(" ").map(String::toInt).let { Triple(it[0] - 1, it[1] - 1, it[2]) }
        outLists[u].add(v to w)
        outLists[v].add(u to w)
    }
    val parents = Array(size) { IntArray(N) { EMPTY }.apply { this[ROOT] = ROOT } }
    val priceArr = LongArray(N)
    val depth = IntArray(N)
    fun dfs(parent: Int) {
        for ((child, price) in outLists[parent]) {
            if (parents[0][child] != EMPTY) continue
            parents[0][child] = parent
            priceArr[child] = priceArr[parent] + price
            depth[child] = depth[parent] + 1
            dfs(child)
        }
    }
    dfs(ROOT)
    for (i in 1 until parents.size) {
        for (j in 0 until N) {
            parents[i][j] = parents[i - 1][parents[i - 1][j]]
        }
    }

    fun depthDiff(u: Int, v: Int): Int = abs(depth[u] - depth[v])
    fun lca(u: Int, v: Int): Int {
        var (A, B) = if (depth[u] > depth[v]) v to u else u to v
        if (depth[A] != depth[B]) {
            val diff = depthDiff(A, B)
            for (i in 0 until size) {
                if ((diff and (1 shl i)) != 0) B = parents[i][B]
            }
        }

        for (i in (size - 1) downTo 0) {
            if (parents[i][A] != parents[i][B]) {
                A = parents[i][A]
                B = parents[i][B]
            }
        }
        return if (A == B) A else parents[0][A]
    }

    fun kth(u: Int, v: Int, k: Int): Int {
        val lca = lca(u, v)
        var A = u
        var B = v
        if (depthDiff(A, lca) >= (k - 1)) {
            val kk = k - 1
            for (i in 0 until size) {
                if ((kk and (1 shl i)) != 0) A = parents[i][A]
            }
            return A
        } else {
            val kk = depthDiff(A, lca) + depthDiff(B, lca) + 1 - k
            for (i in 0 until size) {
                if ((kk and (1 shl i)) != 0) B = parents[i][B]
            }
            return B
        }
    }

    val sb = StringBuilder()
    val M = readln().toInt()
    repeat(M) {
        val input = readln().split(" ").map(String::toInt)
        val type = input[0]
        val u = input[1] - 1
        val v = input[2] - 1
        val k = input.getOrElse(3) { 0 }
        if (type == 1) sb.appendLine(priceArr[u] + priceArr[v] - 2 * priceArr[lca(u, v)])
        else sb.appendLine(kth(u, v, k) + 1)
    }
    println(sb)
}