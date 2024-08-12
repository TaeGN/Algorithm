package 백준.Platinum.P5.p13016_내왼손에는흑염룡이잠들어있다

import java.io.StreamTokenizer
import kotlin.math.max

const val BEFORE = -1

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val roadLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(N - 1) {
        val from = nextInt()
        val to = nextInt()
        val length = nextInt()
        roadLists[from].add(to to length)
        roadLists[to].add(from to length)
    }
    fun IntArray.setDP(from: Int, totalLength: Int = 0) {
        this[from] = max(this[from], totalLength)
        for ((to, length) in roadLists[from]) {
            if (this[to] == BEFORE) setDP(to, totalLength + length)
        }
    }

    fun IntArray.maxLengthIdx(): Int = mapIndexed { index, i -> index to i }.maxBy { it.second }.first
    val dp1 = IntArray(N + 1) { BEFORE }
    dp1.setDP(1)
    val idx1 = dp1.maxLengthIdx()
    dp1.fill(BEFORE)
    dp1.setDP(idx1)
    val idx2 = dp1.maxLengthIdx()
    val dp2 = IntArray(N + 1) { BEFORE }
    dp2.setDP(idx2)
    val sb = StringBuilder()
    for (i in 1..N) {
        sb.appendLine(max(dp1[i], dp2[i]))
    }
    println(sb)
}