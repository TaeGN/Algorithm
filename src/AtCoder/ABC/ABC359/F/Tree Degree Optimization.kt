package AtCoder.ABC.ABC359.F

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first.toLong() * (2 * it.second + 1) })
    var result = 0L
    repeat(N) {
        val num = nextInt()
        pq.add(num to 1)
        result += num
    }
    for (i in 0 until (N - 2)) {
        val (num, count) = pq.poll()
        result += num.toLong() * (2 * count + 1)
        pq.add(num to (count + 1))
    }
    println(result)
}