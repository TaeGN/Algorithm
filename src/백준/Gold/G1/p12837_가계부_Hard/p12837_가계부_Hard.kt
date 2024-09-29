package 백준.Gold.G1.p12837_가계부_Hard

class SegTree(val N: Int) {
    private val tree = LongArray(4 * (N + 1))
    fun update(idx: Int, diff: Int, treeIdx: Int = 1, start: Int = 1, end: Int = N) {
        if (end < idx || idx < start) return
        tree[treeIdx] += diff.toLong()
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, diff, treeIdx * 2, start, mid)
        update(idx, diff, treeIdx * 2 + 1, mid + 1, end)
    }

    fun query(left: Int, right: Int, treeIdx: Int = 1, start: Int = 1, end: Int = N): Long {
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, treeIdx * 2, start, mid) + query(left, right, treeIdx * 2 + 1, mid + 1, end)
    }
}

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val segTree = SegTree(N)
    val sb = StringBuilder()
    repeat(Q) {
        val (type, A, B) = readln().trim().split(" ").map(String::toInt)
        if (type == 1) segTree.update(A, B)
        else sb.appendLine(segTree.query(A, B))
    }
    println(sb)
}