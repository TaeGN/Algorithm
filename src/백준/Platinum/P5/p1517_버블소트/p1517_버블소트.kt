package 백준.Platinum.P5.p1517_버블소트

class SegTree(val N: Int) {
    val tree = LongArray(N * 4)

    fun update(idx: Int, diff: Int, start: Int = 0, end: Int = N - 1, treeIdx: Int = 1) {
        if (end < idx || idx < start) return
        tree[treeIdx] += diff.toLong()
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, diff, start, mid, treeIdx * 2)
        update(idx, diff, mid + 1, end, treeIdx * 2 + 1)
    }

    fun query(left: Int, right: Int, start: Int = 1, end: Int = N - 1, treeIdx: Int = 1): Long {
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }
}

fun main() {
    val N = readln().toInt()
    val A = readln().trim().split(" ").map(String::toInt).mapIndexed { index, i -> index to i }.sortedBy { it.second }
    val segTree = SegTree(N)
    var result = 0L
    for ((idx, _) in A) {
        result += segTree.query(idx + 1, N - 1)
        segTree.update(idx, 1)
    }
    println(result)
}