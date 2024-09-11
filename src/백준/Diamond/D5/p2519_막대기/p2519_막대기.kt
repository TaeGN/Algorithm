package 백준.Diamond.D5.p2519_막대기

const val EMPTY = -1

class Line(val p1: Point, val p2: Point) {
    constructor(arr: List<Int>) : this(Point(arr[0], arr[1]), Point(arr[2], arr[3]))
}

class Point(val x: Int, val y: Int)

fun main() {
    fun ccw(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Int =
        when ((x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3)) {
            in 1..Int.MAX_VALUE -> 1
            0 -> 0
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

    val N = readln().toInt()
    fun Int.not() = if (this < N * 3) (this + N * 3) else (this - N * 3)
    val outLists = List(6 * N) { mutableListOf<Int>() }
    for (i in 0 until N) {
        for (j in 0 until 3) {
            outLists[i * 3 + j].add((i * 3 + (j + 1) % 3).not())
            outLists[i * 3 + j].add((i * 3 + (j + 2) % 3).not())
        }
    }

    val lineList = mutableListOf<Line>()
    repeat(3 * N) { idx1 ->
        val line = readln().split(" ").map(String::toInt).let { Line(it) }
        for ((idx2, oLine) in lineList.withIndex()) {
            if (line.isCross(oLine)) {
                outLists[idx1.not()].add(idx2)
                outLists[idx2.not()].add(idx1)
            }
        }
        lineList.add(line)
    }

    val stack = ArrayDeque<Int>()
    val parentArr = IntArray(6 * N) { EMPTY }
    val sccIdArr = IntArray(6 * N) { EMPTY }
    var id = 0
    var sccId = 0
    var isPossible = true
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
            else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
        }
        if (parent == parentArr[from]) {
            sccId++
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccId
                if (sccIdArr[idx.not()] == sccId) isPossible = false
                if (idx == from) break
            }
        }
        return parent
    }
    for (i in 0 until (6 * N)) {
        if (sccIdArr[i] == EMPTY) scc(i)
    }

    fun result(): String {
        if (!isPossible) return "-1"
        val list = mutableListOf<Int>()
        for (i in 0 until 3 * N) {
            if (sccIdArr[i] < sccIdArr[i.not()]) list.add(i)
        }
        val sb = StringBuilder().appendLine(list.size)
        sb.appendLine(list.sorted().joinToString(" ") { "${it + 1}" })
        return sb.toString()
    }
    println(result())
}