package 백준.Gold.G3.p13160_최대클리크구하기

import java.io.StreamTokenizer
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.max

const val ID = 0
const val START = 1
const val END = 2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val lineList = mutableListOf<IntArray>()
    repeat(N) { idx ->
        lineList.add(intArrayOf(idx + 1, nextInt(), nextInt()))
    }
    lineList.sortWith(compareBy({ it[START] }, { it[END] }))
    val pq = PriorityQueue<IntArray>(Comparator.comparingInt { it[END] })
    var curPos = 0
    var maxCount = 0
    var maxCountPos = 0
    for (line in lineList) {
        curPos = line[START]
        pq.add(line)
        while (pq.peek()[END] < curPos) pq.poll()
        if (maxCount < pq.size) {
            maxCount = pq.size
            maxCountPos = curPos
        }
        maxCount = max(maxCount, pq.size)
    }

    val sb = StringBuilder().appendLine(maxCount)
    for (line in lineList) {
        if (maxCountPos in line[START]..line[END]) sb.append("${line[ID]} ")
    }
    println(sb)
}