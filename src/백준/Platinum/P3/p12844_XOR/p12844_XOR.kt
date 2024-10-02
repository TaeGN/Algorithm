package 백준.Platinum.P3.p12844_XOR

import java.io.StreamTokenizer
import kotlin.math.ceil
import kotlin.math.log2

class SegTree(val N: Int) {
    private val tree = IntArray(1 shl (1 + ceil(log2(N.toDouble())).toInt()))
    private val lazy = IntArray(tree.size)

    private fun updateLazy(start: Int, end: Int, treeIdx: Int) {
        if (lazy[treeIdx] != 0) {
            if ((start + end) % 2 == 0) tree[treeIdx] = tree[treeIdx] xor lazy[treeIdx]
            if (start != end) {
                lazy[treeIdx * 2] = lazy[treeIdx * 2] xor lazy[treeIdx]
                lazy[treeIdx * 2 + 1] = lazy[treeIdx * 2 + 1] xor lazy[treeIdx]
            }
        }
        lazy[treeIdx] = 0
    }

    fun update(left: Int, right: Int, num: Int, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1) {
        updateLazy(start, end, treeIdx)
        if (end < left || right < start) return
        if (left <= start && end <= right) {
            if ((start + end) % 2 == 0) tree[treeIdx] = tree[treeIdx] xor num
            if (start != end) {
                lazy[treeIdx * 2] = lazy[treeIdx * 2] xor num
                lazy[treeIdx * 2 + 1] = lazy[treeIdx * 2 + 1] xor num
            }
            return
        }
        val mid = (start + end) / 2
        update(left, right, num, start, mid, treeIdx * 2)
        update(left, right, num, mid + 1, end, treeIdx * 2 + 1)
        tree[treeIdx] = tree[treeIdx * 2] xor tree[treeIdx * 2 + 1]
    }

    fun query(left: Int, right: Int, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1): Int {
        updateLazy(start, end, treeIdx)
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) xor query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val segTree = SegTree(N)
    val sb = StringBuilder()
    repeat(N) { idx -> segTree.update(idx, idx, nextInt()) }
    repeat(nextInt()) {
        if (nextInt() == 1) segTree.update(nextInt(), nextInt(), nextInt())
        else sb.appendLine(segTree.query(nextInt(), nextInt()))
    }
    println(sb)
}