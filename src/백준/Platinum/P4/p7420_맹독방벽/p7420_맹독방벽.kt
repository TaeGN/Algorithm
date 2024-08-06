package 백준.Platinum.P4.p7420_맹독방벽

import java.io.StreamTokenizer
import kotlin.math.PI
import kotlin.math.roundToInt
import kotlin.math.sqrt

class Point(val x: Int, val y: Int) {
    fun dist(o: Point) = (x - o.x) * (x - o.x) + (y - o.y) * (y - o.y)
    fun length(o: Point): Double = sqrt(dist(o).toDouble())
    override fun toString(): String = "$x $y"
}

class Stack<T>(private val list: MutableList<T> = mutableListOf()) : Iterable<T> {
    fun push(value: T) = list.add(value)
    fun pop() = list.removeLast()
    fun first() = list.last()
    fun second() = list[list.size - 2]
    fun size() = list.size
    operator fun get(idx: Int) = list[(idx + size()) % size()]
    override fun iterator(): Iterator<T> = list.iterator()
    override fun toString(): String = list.toString()
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun ccw(p1: Point, p2: Point, p3: Point): Int =
        ((p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y)).compareTo(0)

    val N = nextInt()
    val L = nextInt()
    val pointList = mutableListOf<Point>()
    repeat(N) {
        pointList.add(Point(nextInt(), nextInt()))
    }
    val p1 = pointList.minWith(compareBy(Point::x, Point::y))
    pointList.sortWith { p2, p3 ->
        val ccw = ccw(p1, p2, p3)
        if (ccw == 0) p1.dist(p2).compareTo(p1.dist(p3))
        else -ccw
    }

    val stack = Stack<Point>()
    for (p in pointList) {
        if (stack.size() < 2) stack.push(p)
        else {
            while (stack.size() >= 2 && ccw(stack.second(), stack.first(), p) <= 0) stack.pop()
            stack.push(p)
        }
    }
    val straightLength = stack.foldIndexed(0.0) { index, acc, point -> acc + point.length(stack[index + 1]) }
    val curveLength = 2 * PI * L
    println((straightLength + curveLength).roundToInt())
}