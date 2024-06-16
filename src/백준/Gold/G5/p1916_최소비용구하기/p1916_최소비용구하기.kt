package 백준.Gold.G5.p1916_최소비용구하기

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val priceLists = List(n + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(m) {
        val from = readInt()
        val to = readInt()
        val price = readInt()
        priceLists[from].add(to to price)
    }

    val start = readInt()
    val end = readInt()
    fun minPrice(start: Int, end: Int, priceLists: List<MutableList<Pair<Int, Int>>>): Int {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy({ it.second }, { it.first }))
        val minPriceArr = IntArray(n + 1) { Int.MAX_VALUE }
        val visited = BooleanArray(n + 1)
        minPriceArr[start] = 0
        pq.add(start to minPriceArr[start])

        while (pq.isNotEmpty()) {
            val (curPos, minPrice) = pq.poll()
            if (visited[curPos]) continue
            visited[curPos] = true
            if (curPos == end) return minPrice

            for ((nextPos, price) in priceLists[curPos]) {
                if (minPriceArr[nextPos] > minPrice + price) {
                    minPriceArr[nextPos] = minPrice + price
                    pq.add(nextPos to minPriceArr[nextPos])
                }
            }
        }

        return -1
    }

    println(minPrice(start, end, priceLists))
}