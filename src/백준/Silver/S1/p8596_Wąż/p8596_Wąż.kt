package 백준.Silver.S1.p8596_Wąż

import java.util.StringTokenizer

const val EMPTY = '.'
const val MAX_M = 1000

enum class Direction(val dr: Int, val dc: Int) {
    N(-1, 0),
    E(0, 1),
    S(1, 0),
    W(0, -1);

    fun turnLeft() = Direction.entries[(ordinal + entries.size - 1) % entries.size]
    fun turnRight() = Direction.entries[(ordinal + 1) % entries.size]
}

class Snake(val map: List<CharArray>, var r: Int, var c: Int, var direction: Direction) {
    val snakeSet = mutableSetOf<Int>()
    val snakeQueue = ArrayDeque<Int>()

    init {
        snakeSet.add(r * MAX_M + c)
        snakeQueue.add(r * MAX_M + c)
    }

    fun moveRight(): Boolean {
        direction = direction.turnRight()
        return moveStraight()
    }

    fun moveLeft(): Boolean {
        direction = direction.turnLeft()
        return moveStraight()
    }

    fun moveStraight(): Boolean {
        val nr = r + direction.dr
        val nc = c + direction.dc
        if (isNotValid(nr, nc)) return false
        if (!snakeSet.add(nr * MAX_M + nc)) return false
        if (map[nr][nc] == EMPTY) snakeSet.remove(snakeQueue.removeFirst())
        else map[nr][nc] = EMPTY
        snakeQueue.add(nr * MAX_M + nc)
        r = nr
        c = nc
        return true
    }

    private fun isNotValid(r: Int, c: Int) = !isValid(r, c)
    private fun isValid(r: Int, c: Int) = r in map.indices && c in map[0].indices
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, r) = readLine().split(" ").map(String::toInt).let { Triple(it[0], it[1], it[2]) }
    val direction = Direction.valueOf(readLine())
    val map = List(n) { CharArray(m) }
    var sr = 0
    var sc = 0
    repeat(n) { i ->
        val row = readLine()
        repeat(m) { j ->
            if (row[j] == 'W') {
                sr = i
                sc = j
                map[i][j] = EMPTY
            } else map[i][j] = row[j]
        }
    }
    val snake = Snake(map, sr, sc, direction)
    val st = StringTokenizer(readLine())
    fun result(): String {
        repeat(r) { idx ->
            if (!when (st.nextToken()) {
                    "N" -> snake.moveStraight()
                    "P" -> snake.moveRight()
                    "L" -> snake.moveLeft()
                    else -> throw IllegalArgumentException()
                }
            ) return "${idx + 1}"
        }
        return "OK"
    }
    println(result())
}