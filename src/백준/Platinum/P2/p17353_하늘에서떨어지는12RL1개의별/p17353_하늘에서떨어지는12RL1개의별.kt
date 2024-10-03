package 백준.Platinum.P2.p17353_하늘에서떨어지는12RL1개의별

import java.io.StreamTokenizer
import kotlin.math.ceil
import kotlin.math.log2

class SegTree(val N: Int) {
    private val treeSize = 1 shl (1 + ceil(log2((N + 2).toDouble())).toInt())
    private val tree = LongArray(treeSize)
    private val lazy = LongArray(treeSize)
    fun query(left: Int, right: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1): Long {
        updateLazy(start, end, treeIdx)
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }

    fun updateRange(left: Int, right: Int, value: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1) {
        updateLazy(start, end, treeIdx)
        if (end < left || right < start) return
        if (left <= start && end <= right) {
            update(start, end, treeIdx, value.toLong())
            return
        }
        val mid = (start + end) / 2
        updateRange(left, right, value, start, mid, treeIdx * 2)
        updateRange(left, right, value, mid + 1, end, treeIdx * 2 + 1)
        tree[treeIdx] = tree[treeIdx * 2] + tree[treeIdx * 2 + 1]
    }


    private fun updateLazy(start: Int, end: Int, treeIdx: Int) {
        if (lazy[treeIdx] != 0L) update(start, end, treeIdx, lazy[treeIdx])
        lazy[treeIdx] = 0
    }

    private fun update(start: Int, end: Int, treeIdx: Int, value: Long) {
        tree[treeIdx] += (end - start + 1) * value
        if (start != end) {
            lazy[treeIdx * 2] += value
            lazy[treeIdx * 2 + 1] += value
        }
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val segTree = SegTree(N)
    val A = IntArray(N) { nextInt() }
    for (i in 1..N) {
        segTree.updateRange(i, i, A[i - 1] - A.getOrElse(i - 2) { 0 })
    }
    val sb = StringBuilder()
    repeat(nextInt()) {
        if (nextInt() == 1) {
            val l = nextInt()
            val r = nextInt()
            segTree.updateRange(l, r, 1)
            segTree.updateRange(r + 1, r + 1, -(r - l + 1))
        } else sb.appendLine(segTree.query(1, nextInt()))
    }
    println(sb)
}