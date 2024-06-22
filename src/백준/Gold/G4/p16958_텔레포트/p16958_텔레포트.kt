package 백준.Gold.G4.p16958_텔레포트

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.min

data class City(private val cityInfo: Int, val x: Int, val y: Int) {
    val isSpecial = cityInfo == 1
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val T = readInt()
    fun City.distance(other: City): Int {
        val distance = abs(x - other.x) + abs(y - other.y)
        if (isSpecial && other.isSpecial) return min(T, distance)
        return distance
    }

    val cities = mutableListOf<City>()
    val minDistance = List(N) { IntArray(N) }
    repeat(N) { idxA ->
        val s = readInt()
        val x = readInt()
        val y = readInt()
        val cityA = City(s, x, y)
        cities.add(cityA)
        repeat(idxA) { idxB ->
            val cityB = cities[idxB]
            val distance = cityA.distance(cityB)
            minDistance[idxA][idxB] = distance
            minDistance[idxB][idxA] = distance
        }
    }

    for (mid in 0 until N) {
        for (start in 0 until N) {
            if (start == mid) continue
            for (end in 0 until N) {
                minDistance[start][end] = min(minDistance[start][end], minDistance[start][mid] + minDistance[mid][end])
            }
        }
    }

    val M = readInt()
    val sb = StringBuilder()
    repeat(M) {
        val idxA = readInt() - 1
        val idxB = readInt() - 1
        sb.appendLine(minDistance[idxA][idxB])
    }

    println(sb)
}