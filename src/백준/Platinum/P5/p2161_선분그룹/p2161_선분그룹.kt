package 백준.Platinum.P5.p2161_선분그룹

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun canMeet(other: Line): Boolean {
        val ccw1 = this.ccw(other)
        val ccw2 = other.ccw(this)
        if (ccw1 == 0 && ccw2 == 0) return min(x1, x2) <= max(other.x1, other.x2)
                && min(other.x1, other.x2) <= max(x1, x2)
                && min(y1, y2) <= max(other.y1, other.y2)
                && min(other.y1, other.y2) <= max(y1, y2)
        return ccw1 <= 0 && ccw2 <= 0
    }

    private fun ccw(other: Line): Int = ccw(other.x1, other.y1) * ccw(other.x2, other.y2)
    private fun ccw(x3: Int, y3: Int): Int = when ((x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3)) {
        in 1..Int.MAX_VALUE -> 1
        in Int.MIN_VALUE..-1 -> -1
        else -> 0
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun IntArray.find(idx: Int): Int = if (this[idx] == idx) idx
    else find(this[idx]).also { this[idx] = it }

    fun IntArray.union(idx1: Int, idx2: Int) {
        val root1 = find(idx1)
        val root2 = find(idx2)
        this[root1] = root2
    }

    val n = readInt()
    val lineGroupIdArr = IntArray(n) { it }
    val lineList = mutableListOf<Line>()
    repeat(n) {
        val line = Line(readInt(), readInt(), readInt(), readInt())
        val lineIdx = lineList.size
        for ((otherLineIdx, otherLine) in lineList.withIndex()) {
            if (line.canMeet(otherLine)) lineGroupIdArr.union(otherLineIdx, lineIdx)
        }
        lineList.add(line)
    }

    repeat(n) { idx ->
        lineGroupIdArr.find(idx)
    }

    lineGroupIdArr.asSequence().groupingBy { it }.eachCount().let { println("${it.count()}\n${it.values.max()}") }
}