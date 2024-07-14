package 백준.Gold.G4.p6497_전력난

import java.io.StreamTokenizer

const val MAX_M = 200_000
const val MAX_N = 200_000

data class Road(var x: Int = 0, var y: Int = 0, var z: Int = 0) : Comparable<Road> {
    override fun compareTo(other: Road): Int = z.compareTo(other.z)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun IntArray.find(id: Int): Int = if (this[id] == id) id else find(this[id]).also { this[id] = it }
    fun IntArray.union(id1: Int, id2: Int): Boolean =
        (find(id1) != find(id2)).also { if (it) this[find(id2)] = find(id1) }

    val sb = StringBuilder()
    val parents = IntArray(MAX_M)
    val roadArr = Array(MAX_N) { Road() }
    var m = nextInt()
    var n = nextInt()
    while (m != 0 || n != 0) {
        for (i in 0 until m) {
            parents[i] = i
        }

        var totalPrice = 0
        repeat(n) { idx ->
            roadArr[idx].x = nextInt()
            roadArr[idx].y = nextInt()
            roadArr[idx].z = nextInt()
            totalPrice += roadArr[idx].z
        }
        roadArr.sort(toIndex = n)

        var count = 1
        for (i in 0 until n) {
            val road = roadArr[i]
            if (parents.union(road.x, road.y)) {
                totalPrice -= road.z
                if (++count == m) break
            }
        }
        sb.appendLine(totalPrice)
        m = nextInt()
        n = nextInt()
    }

    println(sb)
}