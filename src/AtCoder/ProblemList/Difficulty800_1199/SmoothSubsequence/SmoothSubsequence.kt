package AtCoder.ProblemList.Difficulty800_1199.SmoothSubsequence

const val MAX_A = 500000

class SegTree {
    private val tree = IntArray((MAX_A + 1) * 4)

    fun update(idx: Int, value: Int, start: Int = 1, end: Int = MAX_A, treeIdx: Int = 1) {
        if (end < idx || idx < start) return
        tree[treeIdx] = maxOf(tree[treeIdx], value)
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, value, start, mid, treeIdx * 2)
        update(idx, value, mid + 1, end, treeIdx * 2 + 1)
    }

    fun query(left: Int, right: Int, start: Int = 1, end: Int = MAX_A, treeIdx: Int = 1): Int {
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return maxOf(query(left, right, start, mid, treeIdx * 2), query(left, right, mid + 1, end, treeIdx * 2 + 1))
    }
}

fun main() {
    val (N, D) = readln().trim().split(" ").map(String::toInt)
    val segTree = SegTree()
    for (a in readln().trim().split(" ").map(String::toInt)) {
        val maxLen = segTree.query(maxOf(1, a - D), minOf(MAX_A, a + D))
        segTree.update(a, maxLen + 1)
    }
    println((1..MAX_A).maxOf { segTree.query(it, it) })
}