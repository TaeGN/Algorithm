package 백준.Platinum.P5.p2243_사탕상자

import kotlin.math.ceil
import kotlin.math.log2


class SegTree(val N: Int) {
    private val tree = IntArray(1 shl (ceil(log2((1 + N).toDouble())).toInt() + 1))

    fun update(idx: Int, count: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1) {
        if (end < idx || idx < start) return
        tree[treeIdx] += count
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, count, start, mid, treeIdx * 2)
        update(idx, count, mid + 1, end, treeIdx * 2 + 1)
    }

    fun query(k: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1): Int {
        if (start == end) return start
        val mid = (start + end) / 2
        val leftCount = tree[treeIdx * 2]
        return if (k <= leftCount) query(k, start, mid, treeIdx * 2)
        else query(k - leftCount, mid + 1, end, treeIdx * 2 + 1)
    }
}

fun main() {
    val N = readln().toInt()
    val segTree = SegTree(1000000)
    val sb = StringBuilder()
    repeat(N) {
        val input = readln().trim().split(" ").map(String::toInt)
        val type = input[0]
        if (type == 1) segTree.query(input[1]).let { segTree.update(it, -1); sb.appendLine(it) }
        else segTree.update(input[1], input[2])
    }
    println(sb)
}