package 백준.Gold.G3.p14618_총깡총깡

import java.io.StreamTokenizer
import java.util.PriorityQueue

const val TYPE_EMPTY = 0
const val TYPE_A = 1
const val TYPE_B = 2
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val J = nextInt()
    val K = nextInt()
    val typeArr = IntArray(N + 1)
    repeat(K) {
        typeArr[nextInt()] = TYPE_A
    }
    repeat(K) {
        typeArr[nextInt()] = TYPE_B
    }

    val roadLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(M) {
        val X = nextInt()
        val Y = nextInt()
        val Z = nextInt()
        roadLists[X].add(Y to Z)
        roadLists[Y].add(X to Z)
    }

    val minDistanceArr = IntArray(N + 1) { Int.MAX_VALUE }.apply { this[J] = 0 }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.add(J to minDistanceArr[J])
    while (pq.isNotEmpty()) {
        val (id, distance) = pq.poll()
        if (minDistanceArr[id] < distance) continue
        for (road in roadLists[id]) {
            if (minDistanceArr[road.first] <= distance + road.second) continue
            minDistanceArr[road.first] = distance + road.second
            pq.add(road.first to minDistanceArr[road.first])
        }
    }

    var idx = 0
    var minDistance = Int.MAX_VALUE
    for (i in 1..N) {
        if (i == J || typeArr[i] == TYPE_EMPTY || minDistance < minDistanceArr[i]) continue
        if (minDistance == minDistanceArr[i] && typeArr[i] == TYPE_B) continue
        minDistance = minDistanceArr[i]
        idx = i
    }

    if (minDistance == Int.MAX_VALUE) println(-1)
    else println("${if (typeArr[idx] == TYPE_A) "A" else "B"}\n$minDistance")
}