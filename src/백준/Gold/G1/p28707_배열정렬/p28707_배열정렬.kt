package 백준.Gold.G1.p28707_배열정렬

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val arrA = IntArray(n)
    repeat(n) { idx ->
//        A 값의 범위를 0..9로 설정
        arrA[idx] = readInt() - 1
    }

    val m = readInt()
    val operationList = mutableListOf<Triple<Int, Int, Int>>()
    repeat(m) {
        operationList.add(Triple(readInt(), readInt(), readInt()))
    }

    fun IntArray.toInt(): Int {
        var result = 0
        for (num in this) {
            result = result * 10 + num
        }
        return result
    }

    fun Int.change(idx1: Int, idx2: Int): Int {
        val str = this.toString()
        val sb = StringBuilder("0".repeat(n - str.length)).append(str)
        val c1 = sb[idx1 - 1]
        val c2 = sb[idx2 - 1]
        sb.setCharAt(idx1 - 1, c2)
        sb.setCharAt(idx2 - 1, c1)
        return sb.toString().toInt()
    }

    fun minPrice(): Int {
        val end = arrA.sorted().toIntArray().toInt()
        val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
        val visitedMap = mutableMapOf<Int, Int>()
        val start = arrA.toInt()
        visitedMap[start] = 0
        pq.offer(start to 0)

        while (pq.isNotEmpty()) {
            val (num, totalPrice) = pq.poll()
            if (num == end) return totalPrice
            if (visitedMap[num]!! < totalPrice) continue
            for ((idx1, idx2, price) in operationList) {
                val changedNum = num.change(idx1, idx2)
                val nextTotalPrice = totalPrice + price
                if (visitedMap.getOrDefault(changedNum, Int.MAX_VALUE) > nextTotalPrice) {
                    visitedMap[changedNum] = nextTotalPrice
                    pq.offer(changedNum to nextTotalPrice)
                }
            }
        }

        return -1
    }

    println(minPrice())
}