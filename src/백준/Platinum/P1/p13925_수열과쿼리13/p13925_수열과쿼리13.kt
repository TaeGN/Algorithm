package 백준.Platinum.P1.p13925_수열과쿼리13

import java.io.StreamTokenizer
import kotlin.math.ceil
import kotlin.math.log2

const val MOD = 1_000_000_007

class SegTree(val N: Int) {
    private val tree = IntArray(1 shl (1 + ceil(log2((N + 1).toDouble())).toInt()))
    private val lazy = Array(tree.size) { intArrayOf(0, 1, 0) }

    fun query(left: Int, right: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1): Int {
        updateLazy(start, end, treeIdx)
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return (query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)) % MOD
    }

    fun updateRange(
        left: Int,
        right: Int,
        p: Int = 0,
        q: Int = 1,
        r: Int = 0,
        start: Int = 1,
        end: Int = N,
        treeIdx: Int = 1
    ) {
        updateLazy(start, end, treeIdx)
        if (end < left || right < start) return
        if (left <= start && end <= right) {
            update(p, q, r, start, end, treeIdx)
            return
        }
        val mid = (start + end) / 2
        updateRange(left, right, p, q, r, start, mid, treeIdx * 2)
        updateRange(left, right, p, q, r, mid + 1, end, treeIdx * 2 + 1)
        tree[treeIdx] = (tree[treeIdx * 2] + tree[treeIdx * 2 + 1]) % MOD
    }

    private fun updateLazy(start: Int, end: Int, treeIdx: Int) {
        val (p, q, r) = lazy[treeIdx]
        update(p, q, r, start, end, treeIdx)
        lazy[treeIdx][0] = 0
        lazy[treeIdx][1] = 1
        lazy[treeIdx][2] = 0
    }

    private fun update(p: Int, q: Int, r: Int, start: Int, end: Int, treeIdx: Int) {
        if (r != 0) {
            tree[treeIdx] = ((end - start + 1).toLong() * r % MOD).toInt()
            if (start != end) {
                lazy[treeIdx * 2][0] = 0
                lazy[treeIdx * 2][1] = 1
                lazy[treeIdx * 2][2] = r
                lazy[treeIdx * 2 + 1][0] = 0
                lazy[treeIdx * 2 + 1][1] = 1
                lazy[treeIdx * 2 + 1][2] = r
            }
        }
        if (q != 1) {
            tree[treeIdx] = (tree[treeIdx].toLong() * q % MOD).toInt()
            if (start != end) {
                lazy[treeIdx * 2][0] = (lazy[treeIdx * 2][0].toLong() * q % MOD).toInt()
                lazy[treeIdx * 2][1] = (lazy[treeIdx * 2][1].toLong() * q % MOD).toInt()
                lazy[treeIdx * 2 + 1][0] = (lazy[treeIdx * 2 + 1][0].toLong() * q % MOD).toInt()
                lazy[treeIdx * 2 + 1][1] = (lazy[treeIdx * 2 + 1][1].toLong() * q % MOD).toInt()
            }
        }
        if (p != 0) {
            tree[treeIdx] = ((tree[treeIdx].toLong() + (end - start + 1).toLong() * p % MOD) % MOD).toInt()
            if (start != end) {
                lazy[treeIdx * 2][0] = (lazy[treeIdx * 2][0] + p) % MOD
                lazy[treeIdx * 2 + 1][0] = (lazy[treeIdx * 2 + 1][0] + p) % MOD
            }
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
    val sb = StringBuilder()
    for (i in 1..N) {
        segTree.updateRange(i, i, r = nextInt())
    }
    repeat(nextInt()) {
        when (nextInt()) {
            1 -> segTree.updateRange(nextInt(), nextInt(), p = nextInt())
            2 -> segTree.updateRange(nextInt(), nextInt(), q = nextInt())
            3 -> segTree.updateRange(nextInt(), nextInt(), r = nextInt())
            4 -> sb.appendLine(segTree.query(nextInt(), nextInt()))
        }
    }
    println(sb)
}