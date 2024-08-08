package AtCoder.ABC.ABC362.D

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val arrA = IntArray(N + 1)
    repeat(N) { idx ->
        arrA[idx + 1] = nextInt()
    }
    val roadLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(M) {
        val U = nextInt()
        val V = nextInt()
        val B = nextInt()
        roadLists[U].add(V to B)
        roadLists[V].add(U to B)
    }

    val minDistArr = LongArray(N + 1) { Long.MAX_VALUE }.apply { this[1] = arrA[1].toLong() }
    val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
    pq.add(1 to minDistArr[1])
    while (pq.isNotEmpty()) {
        val (id, minDist) = pq.poll()
        if (minDistArr[id] < minDist) continue
        for ((nextId, dist) in roadLists[id]) {
            if (minDistArr[nextId] > minDist + dist + arrA[nextId]) {
                minDistArr[nextId] = minDist + dist + arrA[nextId]
                pq.add(nextId to minDistArr[nextId])
            }
        }
    }

    val sb = StringBuilder()
    for (i in 2..N) {
        sb.append("${minDistArr[i]} ")
    }
    println(sb)
}