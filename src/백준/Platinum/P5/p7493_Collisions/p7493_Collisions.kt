package 백준.Platinum.P5.p7493_Collisions

import java.util.StringTokenizer
import kotlin.math.ceil
import kotlin.math.log2

class SegTree(val N: Int) {
    private val tree = IntArray(1 shl (ceil(log2(N.toDouble())).toInt() + 1))
    fun update(idx: Int, diff: Int, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1) {
        if (end < idx || idx < start) return
        tree[treeIdx] += diff
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, diff, start, mid, treeIdx * 2)
        update(idx, diff, mid + 1, end, treeIdx * 2 + 1)
    }

    fun query(left: Int, right: Int, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1): Int {
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }
}

data class Ball(val x: Long, val v: Int)

fun main() {
    val N = readln().trim().toInt()
    val ballList = List(N) {
        val st = StringTokenizer(readln())
        Ball(st.nextToken().toLong(), st.nextToken().toInt())
    }.sortedBy { it.x }
    val speedToIdxMap = mutableMapOf<Int, Int>()
    ballList.asSequence().map { it.v }.distinct().sorted().forEachIndexed { index, i -> speedToIdxMap[i] = index }
    val size = speedToIdxMap.size
    val segTree = SegTree(size)
    var result = 0L
    for ((_, v) in ballList) {
        val idx = speedToIdxMap[v]!!
        if (idx < size - 1) result += segTree.query(idx + 1, size - 1)
        segTree.update(idx, 1)
    }
    println(result)
}