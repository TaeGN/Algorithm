package 백준.Gold.G3.p26937_KöpaBöcker

import java.util.PriorityQueue

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val mArr = mutableListOf<Pair<Int, Array<Pair<Int, Int>>>>()
    repeat(M) {
        val (K, deliveryFee) = readln().trim().split(" ").map(String::toInt)
        val priceArr = Array(K) { readln().trim().split(" ").map(String::toInt).let { it[0] to it[1] } }
        mArr.add(deliveryFee to priceArr)
    }

    fun minPrice(count: Int, selected: IntArray): Int {
        var curResult = 0
        val visited = mutableSetOf<Int>()
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        for (i in 0 until count) {
            val m = mArr[selected[i]]
            curResult += m.first
            m.second.forEach { pq.add(it) }
        }
        var bookCount = 0
        while (pq.isNotEmpty() && bookCount < N) {
            val (bookId, bookPrice) = pq.poll()
            if (visited.add(bookId)) {
                bookCount++
                curResult += bookPrice
            }
        }
        return if (bookCount == N) curResult else IMPOSSIBLE
    }

    fun subSet(idx: Int = 0, count: Int = 0, selected: IntArray = IntArray(M)): Int {
        if (idx == M) return minPrice(count, selected)
        selected[count] = idx
        return minOf(subSet(idx + 1, count + 1, selected), subSet(idx + 1, count, selected))
    }

    println(subSet())
}