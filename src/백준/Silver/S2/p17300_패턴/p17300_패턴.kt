package 백준.Silver.S2.p17300_패턴

import java.io.StreamTokenizer

const val PHONE_SIZE = 3

data class Point(val id: Int) {
    private val r: Int = (id - 1) / PHONE_SIZE
    private val c: Int = (id - 1) % PHONE_SIZE

    fun middleId(other: Point): Int? {
        val sumR = this.r + other.r
        val sumC = this.c + other.c
        if (sumR % 2 == 0 && sumC % 2 == 0) return (sumR / 2) * PHONE_SIZE + sumC / 2 + 1
        return null
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val l = readInt()
    fun isPossible(): Boolean {
        var prevPoint = Point(readInt())
        val pattern = mutableSetOf(prevPoint.id)
        repeat(l - 1) {
            val curPoint = Point(readInt())
            if (!pattern.add(curPoint.id)) return false
            val middleId = prevPoint.middleId(curPoint)
            middleId?.let { if (!pattern.contains(it)) return false }
            prevPoint = curPoint
        }
        return true
    }

    println(if (isPossible()) "YES" else "NO")
}