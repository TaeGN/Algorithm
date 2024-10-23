package AtCoder.ProblemList.Difficulty800_1199.Mancala2

class SegTree(val size: Int) {
    private val tree = LongArray(size * 4)
    private val lazy = LongArray(tree.size)

    fun updateRange(left: Int, right: Int, value: Long, start: Int = 0, end: Int = size - 1, treeIdx: Int = 1): Long {
        lazyUpdate(start, end, treeIdx)
        if (end < left || right < start) return 0
        if (left <= start && end <= right) {
            tree[treeIdx] += value * (end - start + 1)
            if (start != end) {
                lazy[treeIdx * 2] += value
                lazy[treeIdx * 2 + 1] += value
            }
            return tree[treeIdx]
        }
        val mid = (start + end) / 2
        tree[treeIdx] = updateRange(left, right, value, start, mid, treeIdx * 2) +
                updateRange(left, right, value, mid + 1, end, treeIdx * 2 + 1)
        return tree[treeIdx]
    }

    fun query(left: Int, right: Int, start: Int = 0, end: Int = size - 1, treeIdx: Int = 1): Long {
        lazyUpdate(start, end, treeIdx)
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }

    private fun lazyUpdate(start: Int, end: Int, treeIdx: Int) {
        if (lazy[treeIdx] != 0L) {
            tree[treeIdx] += lazy[treeIdx] * (end - start + 1)
            if (start != end) {
                lazy[treeIdx * 2] += lazy[treeIdx]
                lazy[treeIdx * 2 + 1] += lazy[treeIdx]
            }
        }
        lazy[treeIdx] = 0
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 0 until size) {
            sb.append("${this.query(i, i)} ")
        }
        return sb.toString()
    }
}

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val segTree = SegTree(N)
    for (i in 0 until N) {
        segTree.updateRange(i, i, A[i].toLong())
    }
    for (i in readln().trim().split(" ").map(String::toInt)) {
        val value = segTree.query(i, i)
        val p = value / N
        val q = (value % N).toInt()
        segTree.updateRange(i, i, -value)
        segTree.updateRange(0, N - 1, p)
        if (i < N - 1) segTree.updateRange(i + 1, minOf(i + q, N - 1), 1)
        if (i + q > N - 1) segTree.updateRange(0, q - (N - i), 1)
    }
    val sb = StringBuilder()
    for (i in 0 until N) {
        sb.append("${segTree.query(i, i)} ")
    }
    println(sb)
}