package 백준.Gold.G3.p1516_게임개발

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val sequenceList = List(N + 1) { mutableListOf<Int>() }
    val inDegreeArr = IntArray(N + 1) { if (it == 0) Int.MIN_VALUE else 0 }
    val timeArr = IntArray(N + 1)
    repeat(N) { idx ->
        val id = idx + 1
        timeArr[id] = readInt()
        var prevId = readInt()
        while (prevId != -1) {
            sequenceList[prevId].add(id)
            inDegreeArr[id]++
            prevId = readInt()
        }
    }

    val resultTimeArr = IntArray(N + 1)
    val pq = ArrayDeque<Pair<Int, Int>>()
    for ((id, inDegree) in inDegreeArr.withIndex()) {
        if (inDegree == 0) {
            resultTimeArr[id] = timeArr[id]
            pq.add(id to timeArr[id])
        }
    }

    while (pq.isNotEmpty()) {
        val (id, time) = pq.removeFirst()
        for (nextId in sequenceList[id]) {
            resultTimeArr[nextId] = max(resultTimeArr[nextId], time + timeArr[nextId])
            if (--inDegreeArr[nextId] == 0) {
                pq.add(nextId to resultTimeArr[nextId])
            }
        }
    }

    val sb = StringBuilder()
    for (id in 1..N) {
        sb.appendLine(resultTimeArr[id])
    }
    println(sb)
}