package 백준.Gold.G3.p16158_회식구호

import java.io.StreamTokenizer
import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

data class Number(val top: Int, val bottom: Int) : Comparable<Number> {
    constructor(top: Int) : this(top, 1)

    override fun compareTo(other: Number): Int = (top * other.bottom).compareTo(other.top * bottom)
    override fun toString(): String {
        if (bottom == 1) return "$top"
        val gcd = gcd(max(top, bottom), min(top, bottom))
        val newTop = top / gcd
        val newBottom = bottom / gcd
        return "$newTop${if (newBottom == 1) "" else "/$newBottom"}"
    }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val X = readInt()
    val K = readInt()
    val minPq = PriorityQueue<Number>()
    val maxPq = PriorityQueue<Number>()
    repeat(N) {
        val num = readInt()
        minPq.add(Number(num * X, 100))
        maxPq.add(Number(num * (200 - X), 100))
    }
    var count = 0
    var result = Number(-1)
    while (minPq.isNotEmpty() && maxPq.isNotEmpty()) {
        if (minPq.peek() <= maxPq.peek()) {
            val curNum = minPq.poll()
            if (++count == K) {
                result = curNum
                break
            }
        } else {
            count--
            maxPq.poll()
        }
    }

    println(result)
}