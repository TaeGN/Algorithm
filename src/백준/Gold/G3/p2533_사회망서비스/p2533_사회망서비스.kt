package 백준.Gold.G3.p2533_사회망서비스

import java.io.StreamTokenizer


const val EARLY_ADOPTER = 0
const val NOT_EARLY_ADOPTER = 1
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val dp = Array(n) { arrayOf(1, 0) }
    val friendLists = List(n) { mutableListOf<Int>() }
    val visited = BooleanArray(n)
    repeat(n - 1) {
        val from = readInt() - 1
        val to = readInt() - 1
        friendLists[from].add(to)
        friendLists[to].add(from)
    }

    fun visit(idx: Int) {
        visited[idx] = true
        for (friend in friendLists[idx]) {
            if (visited[friend]) continue
            visit(friend)
            dp[idx][EARLY_ADOPTER] += dp[friend].min()
            dp[idx][NOT_EARLY_ADOPTER] += dp[friend][EARLY_ADOPTER]
        }
    }

    val root = 0
    visit(root)
    println(dp[root].min())
}