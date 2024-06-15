package 백준.Gold.G4.p30797_가희와여행가요

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.max

data class Road(val toIndex: Int, val cost: Int, val time: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val q = readInt()
    val roadLists = List(n + 1) { mutableListOf<Road>() }
    repeat(q) {
        val from = readInt()
        val to = readInt()
        val cost = readInt()
        val time = readInt()
        roadLists[from].add(Road(to, cost, time))
        roadLists[to].add(Road(from, cost, time))
    }

    fun totalTimeAndCost(roadLists: List<MutableList<Road>>): Pair<Int, Long>? {
        val pq = PriorityQueue(roadLists[1].size, compareBy<Road>({ it.cost }, { it.time }))
        val visited = BooleanArray(n + 1)
        pq.addAll(roadLists[1])
        visited[1] = true
        var count = 1
        var totalCost = 0L
        var resultTime = 0

        while (pq.isNotEmpty() && count < n) {
            val road = pq.poll()
            if (visited[road.toIndex]) continue
            visited[road.toIndex] = true
            count++
            roadLists[road.toIndex].asSequence().filter { !visited[it.toIndex] }.forEach { pq.add(it) }
            totalCost += road.cost
            resultTime = max(resultTime, road.time)
        }

        return if (count == n) resultTime to totalCost else null
    }

    println("${totalTimeAndCost(roadLists)?.let { "${it.first} ${it.second}" } ?: -1}")
}