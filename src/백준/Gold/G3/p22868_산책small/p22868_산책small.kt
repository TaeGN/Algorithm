package 백준.Gold.G3.p22868_산책small

import java.io.StreamTokenizer

const val EMPTY = 0
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val roadLists = List(N + 1) { mutableListOf<Int>() }
    repeat(M) {
        val A = nextInt()
        val B = nextInt()
        roadLists[A].add(B)
        roadLists[B].add(A)
    }
    roadLists.forEach { it.sort() }
    val S = nextInt()
    val E = nextInt()

    fun minDistance(
        start: Int,
        end: Int,
        selectedRouteSet: Set<Int> = setOf(),
        inArr: IntArray = IntArray(N + 1).apply { this[start] = start }
    ): Int {
        val queue = ArrayDeque<Int>()
        queue.add(start)
        var distance = 0
        while (queue.isNotEmpty()) {
            distance++
            repeat(queue.size) {
                val from = queue.removeFirst()
                for (to in roadLists[from]) {
                    if (to !in selectedRouteSet && inArr[to] == EMPTY) {
                        inArr[to] = from
                        if (to == end) return distance
                        queue.add(to)
                    }
                }
            }
        }

        throw IllegalArgumentException()
    }

    fun minDistanceAndRoutes(start: Int, end: Int): Pair<Int, Set<Int>> {
        fun IntArray.selectedRouteSet(end: Int): Set<Int> {
            val set = mutableSetOf<Int>()
            var i = end
            while (this[i] != i) {
                set.add(i)
                i = this[i]
            }
            return set
        }

        val inArr = IntArray(N + 1).apply { this[start] = start }
        return minDistance(start, end, inArr = inArr) to inArr.selectedRouteSet(end)
    }

    val (minDistance1, selectedRouteSet) = minDistanceAndRoutes(S, E)
    val minDistance2 = minDistance(E, S, selectedRouteSet = selectedRouteSet)
    println(minDistance1 + minDistance2)
}