package 백준.Gold.G2.p2611_자동차경주

import java.io.StreamTokenizer

const val EMPTY = -1
const val TOTAL = 0
const val START = 1

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val roadLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(M) {
        val from = nextInt()
        val to = nextInt()
        val weight = nextInt()
        roadLists[from].add((if (to == START) TOTAL else to) to weight)
    }

    val maxScoreArr = IntArray(N + 1)
    val routeArr = IntArray(N + 1) {it}
    fun setMaxScore(
        from: Int = START
    ) {
        for ((to, weight) in roadLists[from]) {
            val newMaxScore = maxScoreArr[from] + weight
            if (maxScoreArr[to] < newMaxScore) {
                routeArr[to] = from
                maxScoreArr[to] = newMaxScore
                setMaxScore(to)
            }
        }
    }
    setMaxScore()
    println(maxScoreArr[TOTAL])
    val sb = StringBuilder("$START")
    var i = TOTAL
    while(i != routeArr[i]) {
        sb.insert(0, "${routeArr[i]} ")
        i = routeArr[i]
    }
    println(sb)
}