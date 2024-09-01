package 백준.Gold.G3.p1774_우주신과의교감

import java.util.PriorityQueue
import kotlin.math.sqrt

fun main() {
    fun dist(x1: Int, y1: Int, x2: Int, y2: Int): Double =
        sqrt((x1 - x2).toDouble() * (x1 - x2) + (y1 - y2).toDouble() * (y1 - y2))
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val posArr = Array(N) { readln().trim().split(" ").map(String::toInt).let { it[0] to it[1] } }
    val pq = PriorityQueue<Triple<Int, Int, Double>>(compareBy { it.third })
    for (i in posArr.indices) {
        for (j in posArr.indices) {
            if (i == j) continue
            pq.add(Triple(i, j, dist(posArr[i].first, posArr[i].second, posArr[j].first, posArr[j].second)))
        }
    }

    val parentArr = IntArray(N) { it }
    fun find(id: Int): Int = if (parentArr[id] == id) id else find(parentArr[id]).also { parentArr[id] = it }
    fun union(id1: Int, id2: Int): Boolean {
        if (find(id1) == find(id2)) return false
        parentArr[find(id2)] = find(id1)
        return true
    }
    repeat(M) {
        readln().trim().split(" ").map(String::toInt).let { union(it[0] - 1, it[1] - 1) }
    }
    var result = 0.0
    var count = N - 1
    parentArr.forEachIndexed { index, i -> if (index != i) count-- }
    while (count > 0) {
        val (i, j, dist) = pq.poll()
        if (union(i, j)) {
            result += dist
            count--
        }
    }
    println(String.format("%.2f", result))
}