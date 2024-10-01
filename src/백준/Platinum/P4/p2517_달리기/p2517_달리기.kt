package 백준.Platinum.P4.p2517_달리기

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

fun main() {
    val N = readln().toInt()
    val sb = StringBuilder()
    val numArr = Array(N) { readln().toInt() }
    val map = numArr.asSequence().sorted().mapIndexed { index, i -> i to index }.toMap()
    val tree = SegTree(map.size)
    for ((rank, num) in numArr.withIndex()) {
        val idx = map[num]!!
        sb.appendLine(rank + 1 - (if (idx > 0) tree.query(0, idx) else 0))
        tree.update(idx, 1)
    }
    println(sb)
}