package AtCoder.ABC.ABC359.E

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val hArr = IntArray(N + 1)
    repeat(N) { idx -> hArr[idx + 1] = nextInt() }
    val timeArr = LongArray(N + 1)
    val stack = ArrayDeque<Pair<Int, Int>>()
    stack.addFirst(0 to Int.MAX_VALUE)
    for (idx in 1..N) {
        val h = hArr[idx]
        while (stack.isNotEmpty() && stack.first().second < h) stack.removeFirst()
        timeArr[idx] = timeArr[stack.first().first] + (idx - stack.first().first).toLong() * h
        stack.addFirst(idx to h)
    }
    val sb = StringBuilder()
    for (i in 1..N) {
        sb.append("${timeArr[i] + 1} ")
    }
    println(sb)
}