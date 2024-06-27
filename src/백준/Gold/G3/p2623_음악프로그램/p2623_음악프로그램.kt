package 백준.Gold.G3.p2623_음악프로그램

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val sequenceList = List(N + 1) { mutableListOf<Int>() }
    val inDegreeArr = IntArray(N + 1) { if (it == 0) Int.MIN_VALUE else 0 }
    repeat(M) {
        val size = readInt()
        var prevSinger = readInt()
        repeat(size - 1) {
            val curSinger = readInt()
            sequenceList[prevSinger].add(curSinger)
            inDegreeArr[curSinger]++
            prevSinger = curSinger
        }
    }

    val sb = StringBuilder()
    val queue = ArrayDeque<Int>()
    for ((singer, inDegree) in inDegreeArr.withIndex()) {
        if (inDegree == 0) {
            sb.appendLine(singer)
            queue.add(singer)
        }
    }

    while (queue.isNotEmpty()) {
        val singer = queue.removeFirst()
        for (nextSinger in sequenceList[singer]) {
            if (--inDegreeArr[nextSinger] == 0) {
                sb.appendLine(nextSinger)
                queue.add(nextSinger)
            }
        }
    }

    println(if (inDegreeArr.any { it > 0 }) 0 else sb)
}