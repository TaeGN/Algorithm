package 백준.Platinum.P5.p1708_볼록껍질

import java.io.StreamTokenizer

data class Point(val r: Int, val c: Int)

class Stack<T>(private val list: MutableList<T> = mutableListOf()) {
    fun push(value: T) = list.add(value)
    fun pop() = list.removeLast()
    fun first() = list.last()
    fun second() = list[list.size - 2]
    fun size() = list.size
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun ccw(p1: Point, p2: Point, p3: Point): Int =
        ((p1.r.toLong() * p2.c + p2.r.toLong() * p3.c + p3.r.toLong() * p1.c) -
                (p2.r.toLong() * p1.c + p3.r.toLong() * p2.c + p1.r.toLong() * p3.c)).compareTo(0)

    fun Point.distSquare(other: Point) = (r - other.r).toLong() * (r - other.r) + (c - other.c).toLong() * (c - other.c)

    val N = nextInt()
    val pointList = mutableListOf<Point>()
    repeat(N) {
        pointList.add(Point(nextInt(), nextInt()))
    }
    val root = pointList.minWith(compareBy(Point::r, Point::c))
    pointList.sortWith { o1, o2 ->
        val ccw = ccw(root, o1, o2)
        if (ccw == 0) root.distSquare(o1).compareTo(root.distSquare(o2))
        else -ccw
    }

    val pointStack = Stack<Point>()
    pointStack.push(pointList[0])
    pointStack.push(pointList[1])
    for (idx in 2 until N) {
        while (pointStack.size() >= 2 && ccw(pointStack.second(), pointStack.first(), pointList[idx]) <= 0) {
            pointStack.pop()
        }
        pointStack.push(pointList[idx])
    }
    println(pointStack.size())
}