package 백준.Gold.G3.p17386_선분교차1

class Line(val p1: Point, val p2: Point) {
    constructor(x1: Int, y1: Int, x2: Int, y2: Int) : this(Point(x1, y1), Point(x2, y2))
}

class Point(val x: Int, val y: Int)

fun main() {
    fun ccw(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Int =
        when ((x1.toLong() * y2 + x2.toLong() * y3 + x3.toLong() * y1) - (x2.toLong() * y1 + x3.toLong() * y2 + x1.toLong() * y3)) {
            in 1..Long.MAX_VALUE -> 1
            0L -> 0
            else -> -1
        }

    fun Line.ccw4(o: Line): Int {
        val ccw1 = ccw(p1.x, p1.y, p2.x, p2.y, o.p1.x, o.p1.y)
        val ccw2 = ccw(p1.x, p1.y, p2.x, p2.y, o.p2.x, o.p2.y)
        return ccw1 * ccw2
    }

    fun Line.isCross(o: Line): Boolean {
        val ccw1 = this.ccw4(o)
        val ccw2 = o.ccw4(this)
        if (ccw1 == 0 || ccw2 == 0) {
            if (maxOf(minOf(p1.x, p2.x), minOf(o.p1.x, o.p2.x))
                <= minOf(maxOf(p1.x, p2.x), maxOf(o.p1.x, o.p2.x)) &&
                maxOf(minOf(p1.y, p2.y), minOf(o.p1.y, o.p2.y))
                <= minOf(maxOf(p1.y, p2.y), maxOf(o.p1.y, o.p2.y))
            ) return true
        }
        return ccw1 < 0 && ccw2 < 0
    }

    val line1 = readln().split(" ").map(String::toInt).let { Line(it[0], it[1], it[2], it[3]) }
    val line2 = readln().split(" ").map(String::toInt).let { Line(it[0], it[1], it[2], it[3]) }
    println(if (line1.isCross(line2)) 1 else 0)
}