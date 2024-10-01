package 백준.Platinum.P4.p3653_영화수집

class SegTree(val N: Int) {
    private val tree = IntArray(4 * N)
    fun update(idx: Int, diff: Int, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1) {
        if (end < idx || idx < start) return
        tree[treeIdx] += diff
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, diff, start, mid, treeIdx * 2)
        update(idx, diff, mid + 1, end, treeIdx * 2 + 1)
    }

    fun query(left: Int, right: Int = N - 1, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1): Int {
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }
}

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M) = readln().trim().split(" ").map(String::toInt)
        val segTree = SegTree(N + M)
        val idxArr = IntArray(N + 1)
        var idx = 0
        for (i in N downTo 1) {
            idxArr[i] = idx++
            segTree.update(idxArr[i], 1)
        }
        for (i in readln().trim().split(" ").map(String::toInt)) {
            val pIdx = idxArr[i]
            val nIdx = idx++
            idxArr[i] = nIdx
            sb.append("${segTree.query(pIdx + 1)} ")
            segTree.update(pIdx, -1)
            segTree.update(nIdx, 1)
        }
        sb.appendLine()
    }
    println(sb)
}