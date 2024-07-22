package 백준.Gold.G4.p23835_어떤우유의배달목록_Easy

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val roadLists = List(N + 1) { mutableListOf<Int>() }
    val totalCountArr = IntArray(N + 1)
    val visitedArr = BooleanArray(N + 1)
    val routeArr = IntArray(N + 1)
    repeat(N - 1) {
        val roomA = nextInt()
        val roomB = nextInt()
        roadLists[roomA].add(roomB)
        roadLists[roomB].add(roomA)
    }

    fun delivery(from: Int, to: Int, routeArrIdx: Int = 0): Boolean {
        visitedArr[from] = true
        routeArr[routeArrIdx] = from
        if (from == to) {
            for (i in 0..routeArrIdx) {
                totalCountArr[routeArr[i]] += i
            }
            visitedArr.fill(false)
            return true
        }

        for (next in roadLists[from]) {
            if (!visitedArr[next] && delivery(next, to, routeArrIdx + 1)) return true
        }

        visitedArr[from] = false
        return false
    }

    fun totalCount(target: Int) = totalCountArr[target]
    val sb = StringBuilder()
    val Q = nextInt()
    repeat(Q) {
        if (nextInt() == 1) delivery(nextInt(), nextInt())
        else sb.appendLine(totalCount(nextInt()))
    }
    println(sb)
}