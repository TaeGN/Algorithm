package 백준.Gold.G2.p21609_상어중학교

import java.io.StreamTokenizer

const val EMPTY = -2
const val BLACK = -1
const val RAINBOW = 0

operator fun List<IntArray>.get(point: Point) = this[point.r][point.c]
operator fun List<IntArray>.set(point: Point, value: Int) {
    this[point.r][point.c] = value
}

operator fun List<BooleanArray>.get(point: Point) = this[point.r][point.c]
operator fun List<BooleanArray>.set(point: Point, value: Boolean) {
    this[point.r][point.c] = value
}

fun List<BooleanArray>.reset() = this.forEach { it.fill(false) }

enum class Direction(val dr: Int, val dc: Int) {
    SOUTH(1, 0),
    WEST(0, -1),
    NORTH(-1, 0),
    EAST(0, 1);

    fun rotateCCW90() = Direction.entries[(this.ordinal + 1) % Direction.entries.size]
}

data class Point(val r: Int, val c: Int) {
    companion object {
        private lateinit var pointPool: List<List<Point>>
        fun init(n: Int, m: Int) {
            pointPool = List(n) { r -> List(m) { c -> Point(r, c) } }
        }

        fun of(r: Int = 0, c: Int = 0) = try {
            pointPool[r][c]
        } catch (e: IndexOutOfBoundsException) {
            null
        }

        fun ofWithException(r: Int = 0, c: Int = 0) = of(r, c) ?: throw IndexOutOfBoundsException()
    }

    operator fun plus(other: Point): Point? = of(r + other.r, c + other.c)
}

class BlockMap(val map: List<IntArray>, var d: Direction = Direction.SOUTH) {
    private val n = map.size
    private val m = map[0].size
    private val queue = ArrayDeque<Point>()
    private val visited = List(n) { BooleanArray(m) }
    private val blockGroup: MutableList<Point>
    private val bigBlockGroup: MutableList<Point>
    private var totalCount = 0
    private var rainbowCount = 0

    init {
        Point.init(n, m)
        blockGroup = MutableList(n * m) { Point.ofWithException() }
        bigBlockGroup = MutableList(n * m) { Point.ofWithException() }
    }

    fun run(): Int {
        var totalScore = 0
        do {
            init()
            findBigBlockGroup()
            removeBigBlockGroup()
            gravity()
            rotateCCW90()
            gravity()
            val score = totalCount * totalCount
            totalScore += score
        } while (score != 0)

        return totalScore
    }

    private fun init() {
        totalCount = 0
        rainbowCount = 0
        visited.reset()
    }

    private fun findBigBlockGroup() {
        when (d) {
            Direction.SOUTH -> {
                repeat(0, n - 1) { r ->
                    repeat(0, n - 1) { c ->
                        bfs(Point.ofWithException(r, c))
                    }
                }
            }

            Direction.WEST -> {
                repeat(n - 1, 0) { c ->
                    repeat(0, n - 1) { r ->
                        bfs(Point.ofWithException(r, c))
                    }
                }
            }

            Direction.NORTH -> {
                repeat(n - 1, 0) { r ->
                    repeat(n - 1, 0) { c ->
                        bfs(Point.ofWithException(r, c))
                    }
                }
            }

            Direction.EAST -> {
                repeat(0, n - 1) { c ->
                    repeat(n - 1, 0) { r ->
                        bfs(Point.ofWithException(r, c))
                    }
                }
            }
        }
    }

    private fun repeat(start: Int, end: Int, callback: (i: Int) -> Unit) {
        val range = if (start < end) (start..end) else (start downTo end)
        for (i in range) {
            callback(i)
        }
    }

    private fun bfs(startPoint: Point) {
        when (map[startPoint]) {
            BLACK, RAINBOW, EMPTY -> return
            else -> if (visited[startPoint]) return
        }
        queue.add(startPoint)
        visited[startPoint] = true
        var curTotalCount = 0
        var curRainbowCount = 0

        while (queue.isNotEmpty()) {
            val curPoint = queue.removeFirst()
            blockGroup[curTotalCount++] = curPoint

            for (direction in Direction.entries) {
                val nextPoint = Point.of(curPoint.r + direction.dr, curPoint.c + direction.dc) ?: continue
                if (visited[nextPoint]) continue
                when (map[nextPoint]) {
                    RAINBOW -> curRainbowCount++
                    map[blockGroup[0]] -> {}
                    else -> continue
                }
                visited[nextPoint] = true
                queue.add(nextPoint)
            }
        }

        blockGroup.asSequence().take(curTotalCount).filter { map[it] == RAINBOW }.forEach { visited[it] = false }
        if (curTotalCount < 2 || curTotalCount == curRainbowCount) return
        if (totalCount > curTotalCount) return
        if (totalCount == curTotalCount && rainbowCount > curRainbowCount) return
        totalCount = curTotalCount
        rainbowCount = curRainbowCount
        for (i in 0 until curTotalCount) {
            bigBlockGroup[i] = blockGroup[i]
        }
    }

    private fun removeBigBlockGroup() {
        for (i in 0 until totalCount) {
            map[bigBlockGroup[i]] = EMPTY
        }
    }

    private fun gravity() {
        when (d) {
            Direction.SOUTH -> {
                repeat(0, n - 1) { c ->
                    var floorR = n - 1
                    repeat(n - 1, 0) { r ->
                        when (map[r][c]) {
                            EMPTY -> {}
                            BLACK -> floorR = r - 1
                            else -> {
                                val type = map[r][c]
                                map[r][c] = EMPTY
                                map[floorR--][c] = type
                            }
                        }
                    }
                }
            }

            Direction.WEST -> {
                repeat(0, n - 1) { r ->
                    var floorC = 0
                    repeat(0, n - 1) { c ->
                        when (map[r][c]) {
                            EMPTY -> {}
                            BLACK -> floorC = c + 1
                            else -> {
                                val type = map[r][c]
                                map[r][c] = EMPTY
                                map[r][floorC++] = type
                            }
                        }
                    }
                }
            }

            Direction.NORTH -> {
                repeat(0, n - 1) { c ->
                    var floorR = 0
                    repeat(0, n - 1) { r ->
                        when (map[r][c]) {
                            EMPTY -> {}
                            BLACK -> floorR = r + 1
                            else -> {
                                val type = map[r][c]
                                map[r][c] = EMPTY
                                map[floorR++][c] = type
                            }
                        }
                    }
                }
            }

            Direction.EAST -> {
                repeat(0, n - 1) { r ->
                    var floorC = n - 1
                    repeat(n - 1, 0) { c ->
                        when (map[r][c]) {
                            EMPTY -> {}
                            BLACK -> floorC = c - 1
                            else -> {
                                val type = map[r][c]
                                map[r][c] = EMPTY
                                map[r][floorC--] = type
                            }
                        }
                    }
                }
            }
        }
    }

    private fun rotateCCW90() {
        d = d.rotateCCW90()
    }
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val map = List(n) { IntArray(n) }
    repeat(n) { r ->
        repeat(n) { c ->
            map[r][c] = readInt()
        }
    }

    BlockMap(map).run().let(::println)
}