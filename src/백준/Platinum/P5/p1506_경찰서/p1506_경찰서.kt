package 백준.Platinum.P5.p1506_경찰서

import java.util.StringTokenizer
import kotlin.math.min

const val EMPTY = 0

fun main() {
    val N = readln().toInt()
    val st = StringTokenizer(readln())
    val costArr = IntArray(N)
    repeat(N) { idx -> costArr[idx] = st.nextToken().toInt() }
    val matrix = Array(N) { BooleanArray(N) }
    repeat(N) { r ->
        val input = readln()
        repeat(N) { c -> matrix[r][c] = input[c] == '1' }
    }

    val finishArr = BooleanArray(N)
    val parentArr = IntArray(N)
    val stack = ArrayDeque<Int>()
    var result = 0
    var id = 0
    fun dfs(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in 0 until N) {
            if (matrix[from][to]) {
                if (parentArr[to] == EMPTY) parent = min(parent, dfs(to))
                else if (!finishArr[to]) parent = min(parent, parentArr[to])
            }
        }

        if (parent == parentArr[from]) {
            var minCost = Int.MAX_VALUE
            while (true) {
                val idx = stack.removeFirst()
                minCost = min(minCost, costArr[idx])
                finishArr[idx] = true
                if (idx == from) break
            }
            result += minCost
        }

        return parent
    }

    for (i in 0 until N) {
        if (!finishArr[i]) dfs(i)
    }
    println(result)
}