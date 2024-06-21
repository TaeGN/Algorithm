package 백준.Silver.S3.p2817_ALPS식투표

import java.util.PriorityQueue

const val MIN_SCORE_RATIO = 5
const val SCORE_AGGREGATION_SIZE = 14

fun main() = with(System.`in`.bufferedReader()) {
    val X = readLine().toInt()
    val minScore = X.toDouble() * MIN_SCORE_RATIO / 100
    val n = readLine().toInt()
    val pq = PriorityQueue<Pair<Char, Int>> { o1, o2 -> o2.second.compareTo(o1.second) }
    val staffList = mutableMapOf<Char, Int>()
    repeat(n) {
        val (name, score) = readLine().split(" ").let { it[0][0] to it[1].toInt() }
        if (score >= minScore) {
            staffList[name] = 0
            for (i in 1..SCORE_AGGREGATION_SIZE) {
                pq.add(name to score / i)
            }
        }
    }

    repeat(SCORE_AGGREGATION_SIZE) {
        val (name, _) = pq.poll()
        staffList[name] = staffList[name]!! + 1
    }
    staffList.asSequence().sortedBy { it.key }.map { "${it.key} ${it.value}" }.joinToString("\n").let(::println)
}