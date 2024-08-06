package 백준.Platinum.P2.p10254_고속도로

import java.io.StreamTokenizer

const val MAX_N = 200000

class Point(var x: Int = 0, var y: Int = 0) {
    fun set(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    operator fun minus(o: Point) = Point(x - o.x, y - o.y)
    override fun toString(): String {
        return "$x $y"
    }
}

class Stack<T>(private val list: MutableList<T> = mutableListOf()) {
    fun push(value: T) = list.add(value)
    fun pop() = list.removeLast()
    fun first() = list.last()
    fun second() = list[list.size - 2]
    fun size() = list.size
    fun clear() = list.clear()
    operator fun get(idx: Int) = list[idx % list.size]
    override fun toString(): String {
        return list.toString()
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun ccw(p1: Point, p2: Point, p3: Point): Int =
        ((p1.x.toLong() * p2.y + p2.x.toLong() * p3.y + p3.x.toLong() * p1.y) -
                (p2.x.toLong() * p1.y + p3.x.toLong() * p2.y + p1.x.toLong() * p3.y)).compareTo(0)

    fun Point.dist(o: Point): Long = (x - o.x).toLong() * (x - o.x) + (y - o.y).toLong() * (y - o.y)

    val pointStack = Stack<Point>()
    val pointArr = Array(MAX_N) { Point() }
    val sb = StringBuilder()
    val T = nextInt()
    repeat(T) {
        val n = nextInt()
        var p1 = Point(Int.MAX_VALUE, Int.MAX_VALUE)
        repeat(n) { idx ->
            pointArr[idx].set(nextInt(), nextInt())
            if (p1.x > pointArr[idx].x || (p1.x == pointArr[idx].x && p1.y > pointArr[idx].y)) {
                p1 = pointArr[idx]
            }
        }
        pointArr.asSequence().take(n).sortedWith { p2, p3 ->
            val ccw = ccw(p1, p2, p3)
            if (ccw == 0) p1.dist(p2).compareTo(p1.dist(p3))
            else -ccw
        }.forEach {
            if (pointStack.size() < 2) pointStack.push(it)
            else {
                while (pointStack.size() >= 2 && ccw(pointStack.second(), pointStack.first(), it) <= 0) pointStack.pop()
                pointStack.push(it)
            }
        }

        var maxDist = 0L
        var maxDistP1 = pointStack[0]
        var maxDistP2 = pointStack[1]
        var idx2 = 1
        for (idx1 in 0 until pointStack.size()) {
            val a = pointStack[idx1]
            val b = pointStack[idx1 + 1]
            do {
                val c = pointStack[idx2 + 1] - (pointStack[idx2] - b)
                val dist = pointStack[idx1].dist(pointStack[idx2])
                if (maxDist < dist) {
                    maxDist = dist
                    maxDistP1 = pointStack[idx1]
                    maxDistP2 = pointStack[idx2]
                }
            } while ((ccw(a, b, c) > 0).also { if (it) idx2++ })
        }

        sb.appendLine("$maxDistP1 $maxDistP2")
        pointStack.clear()
    }
    println(sb)
}