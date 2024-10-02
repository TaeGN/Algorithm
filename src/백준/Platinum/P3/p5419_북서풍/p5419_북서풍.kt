package 백준.Platinum.P3.p5419_북서풍

import java.util.StringTokenizer

class SegTree(val N: Int) {
    private val tree = IntArray(N * 4)

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

data class Point(var x: Int, var y: Int) : Comparable<Point> {
    override fun compareTo(other: Point): Int = if (x != other.x) -x.compareTo(other.x) else y.compareTo(other.y)
}

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val pos =
            MutableList(N) { val st = StringTokenizer(readln()); Point(st.nextToken().toInt(), st.nextToken().toInt()) }
        val yToIdxMap =
            pos.asSequence().map { it.y }.sorted().distinct().mapIndexed { index, i -> i to index }.toMap()
        pos.sort()
        val segTree = SegTree(yToIdxMap.size)
        var result = 0L
        for ((_, y) in pos) {
            val idx = yToIdxMap[y]!!
            result += segTree.query(0, idx)
            segTree.update(idx, 1)
        }
        sb.appendLine(result)
    }
    println(sb)
}