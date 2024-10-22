package AtCoder.ProblemList.Difficulty400_799.AMultiply

import java.util.PriorityQueue

fun main() {
    val (N, C) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    fun result(): Long {
        val forwardSumArr = LongArray(N)
        val backwardSumArr = LongArray(N)
        for (i in 0 until N) {
            forwardSumArr[i] = forwardSumArr.getOrElse(i - 1) { 0 } + A[i]
            backwardSumArr[N - 1 - i] = backwardSumArr.getOrElse(N - i) { 0 } + A[N - 1 - i]
        }
        val total = forwardSumArr.last()
        var result = total
        if (C <= 0) {
            val pq = PriorityQueue<Pair<Int, Long>>(compareBy { -it.second })
            pq.add(N to 0)
            backwardSumArr.forEachIndexed { index, l -> pq.add(index to l) }
            result = maxOf(result, (total - pq.peek().second) * C + pq.peek().second)
            for (i in 0 until N) {
                while (pq.isNotEmpty() && pq.peek().first <= i) pq.poll()
                if (pq.isNotEmpty()) result = maxOf(
                    result,
                    (total - forwardSumArr[i] - pq.peek().second) * C + forwardSumArr[i] + pq.peek().second
                )
            }
        } else {
            val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
            pq.add(N to 0)
            backwardSumArr.forEachIndexed { index, l -> pq.add(index to l) }
            result = maxOf(result, (total - pq.peek().second) * C + pq.peek().second)
            for (i in 0 until N) {
                while (pq.isNotEmpty() && pq.peek().first <= i) pq.poll()
                if (pq.isNotEmpty()) result = maxOf(
                    result,
                    (total - forwardSumArr[i] - pq.peek().second) * C + forwardSumArr[i] + pq.peek().second
                )
            }
        }
        return result
    }
    println(result())
}