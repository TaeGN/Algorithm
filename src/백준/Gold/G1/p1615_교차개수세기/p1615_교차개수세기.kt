package 백준.Gold.G1.p1615_교차개수세기

import java.io.StreamTokenizer

class SegTree(val N: Int) {
    val tree = IntArray((N + 1) * 4)
    fun update(idx: Int, diff: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1) {
        if (end < idx || idx < start) return
        tree[treeIdx]++
        if (start == end) return
        val mid = (start + end) / 2
        update(idx, diff, start, mid, treeIdx * 2)
        update(idx, diff, mid + 1, end, treeIdx * 2 + 1)
    }

    fun query(left: Int, right: Int, start: Int = 1, end: Int = N, treeIdx: Int = 1): Int {
        if (end < left || right < start) return 0
        if (left <= start && end <= right) return tree[treeIdx]
        val mid = (start + end) / 2
        return query(left, right, start, mid, treeIdx * 2) + query(left, right, mid + 1, end, treeIdx * 2 + 1)
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val lists = List(N + 1) { mutableListOf<Int>() }
    val segTree = SegTree(N)
    repeat(M) { lists[nextInt()].add(nextInt()) }
    lists.forEach { it.sort() }
    var result = 0L
    for (list in lists) {
        for (e in list) {
            if (e < N) result += segTree.query(e + 1, N)
            segTree.update(e, 1)
        }
    }
    println(result)
}