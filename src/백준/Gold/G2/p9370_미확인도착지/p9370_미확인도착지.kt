package 백준.Gold.G2.p9370_미확인도착지

import java.util.PriorityQueue

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M, T) = readln().split(" ").map(String::toInt)
        val (S, G, H) = readln().split(" ").map(String::toInt)
        val roadLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
        repeat(M) {
            val (A, B, D) = readln().split(" ").map(String::toInt)
            roadLists[A].add(B to D)
            roadLists[B].add(A to D)
        }
        val dp = IntArray(N + 1) { IMPOSSIBLE }
        val parentArr = Array(N + 1) { mutableListOf<Int>() }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        dp[S] = 0
        pq.add(S to dp[S])
        while (pq.isNotEmpty()) {
            val (from, totalWeight) = pq.poll()
            if (dp[from] < totalWeight) continue
            for ((to, weight) in roadLists[from]) {
                val nextTotalWeight = totalWeight + weight
                if (dp[to] >= nextTotalWeight) {
                    if (dp[to] > nextTotalWeight) {
                        parentArr[to].clear()
                        dp[to] = nextTotalWeight
                        pq.add(to to dp[to])
                    }
                    parentArr[to].add(from)
                }
            }
        }


        val possibleArr = BooleanArray(N + 1)
        fun isPossible(id: Int): Boolean {
            if (possibleArr[id]) return true
            var isPossible = false
            for (parent in parentArr[id]) {
                if ((id == G && parent == H) || (id == H && parent == G)) isPossible = true
                else if (parent != 0 && isPossible(parent)) isPossible = true
            }
            return isPossible.also { possibleArr[id] = it }
        }
        for (i in 1..N) {
            isPossible(i)
        }

        val list = mutableListOf<Int>()
        repeat(T) {
            val X = readln().toInt()
            if (possibleArr[X]) list.add(X)
        }
        list.sort()
        sb.appendLine(list.joinToString(" "))
    }
    println(sb)
}