package 백준.Platinum.P3.p9240_로버트후드

import java.io.StreamTokenizer
import kotlin.math.sqrt

class Stack<T> : Iterable<T> {
    private val stack = mutableListOf<T>()
    fun remove() {
        stack.removeLast()
    }

    fun add(pair: T) = stack.add(pair)
    fun first() = stack.last()
    fun second() = stack[stack.size - 2]
    fun size() = stack.size
    operator fun get(idx: Int) = stack[idx % size()]
    override fun iterator(): Iterator<T> = stack.iterator()
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun ccw(pair1: Pair<Int, Int>, pair2: Pair<Int, Int>, pair3: Pair<Int, Int>) =
        (pair1.first * pair2.second + pair2.first * pair3.second + pair3.first * pair1.second) -
                (pair2.first * pair1.second + pair3.first * pair2.second + pair1.first * pair3.second)

    fun dist(pair1: Pair<Int, Int>, pair2: Pair<Int, Int>) =
        (pair1.first - pair2.first) * (pair1.first - pair2.first) + (pair1.second - pair2.second) * (pair1.second - pair2.second)

    val C = nextInt()
    val cArr = Array(C) { nextInt() to nextInt() }
    val origin = cArr.minWith(compareBy({ it.second }, { it.first }))
    cArr.sortWith { o1, o2 ->
        val ccw = ccw(origin, o1, o2)
        if (ccw == 0) dist(origin, o1).compareTo(dist(origin, o2))
        else -ccw
    }
    val stack = Stack<Pair<Int, Int>>()
    for (c in cArr) {
        while (stack.size() >= 2 && ccw(stack.second(), stack.first(), c) <= 0) stack.remove()
        stack.add(c)
    }
    var result = 0
    var idx1 = 0
    var idx2 = 1
    while (true) {
        val p1 = stack[idx1]
        val p2 = stack[idx1 + 1]
        val p3 = stack[idx2]
        val p4 = stack[idx2 + 1]
        val diff = (p3.first - p2.first) to (p3.second - p2.second)
        val newP3 = (p4.first - diff.first) to (p4.second - diff.second)
        if (ccw(p1, p2, newP3) > 0) idx2++
        else if (++idx1 == stack.size()) break
        result = maxOf(result, dist(p1, p3))
    }
    println(sqrt(result.toDouble()))
}