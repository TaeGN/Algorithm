package 백준.Silver.S2.p2713_규현이의사랑을담은문자메시지

const val MAX_R = 21
const val MAX_C = 21
const val WORD_LENGTH = 5

enum class Direction(var dr: Int, var dc: Int) {
    EAST(0, 1),
    SOUTH(1, 0),
    WEST(0, -1),
    NORTH(-1, 0);

    fun next() = Direction.entries[(this.ordinal + 1) % Direction.entries.size]
}

class SpiralPattern(val n: Int, val m: Int) {
    private var r = 0
    private var c = 0
    private var d = Direction.EAST
    private val visited = List(n) { BooleanArray(m) }

    fun next(): Pair<Int, Int> {
        visited[r][c] = true
        val pair = r to c
        val nextR = r + d.dr
        val nextC = c + d.dc
        if (isNotValid(nextR, nextC) || visited[nextR][nextC]) {
            d = d.next()
        }
        r += d.dr
        c += d.dc
        return pair
    }

    private fun isNotValid(r: Int, c: Int): Boolean = !isValid(r, c)
    private fun isValid(r: Int, c: Int): Boolean = try {
        visited[r][c]
        true
    } catch (e: IndexOutOfBoundsException) {
        false
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    val sb = StringBuilder()
    val matrix = List(MAX_R) { BooleanArray(MAX_C) }
    repeat(t) {
        val input = br.readLine().split(" ")
        val n = input[0].toInt()
        val m = input[1].toInt()
        val msg = input.subList(fromIndex = 2, toIndex = input.size).joinToString(separator = " ")
        val spiralPattern = SpiralPattern(n, m)
        matrix.asSequence().take(n).forEach { it.fill(false, 0, m) }

        repeat(msg.length) { idx ->
            val intCode = if (msg[idx] == ' ') 0 else msg[idx].code - 'A'.code + 1
            val code = intCode.toString(2)
            for (i in 0 until WORD_LENGTH) {
                val (r, c) = spiralPattern.next()
                matrix[r][c] = if (WORD_LENGTH - code.length > i) {
                    false
                } else {
                    code[i - WORD_LENGTH + code.length] == '1'
                }
            }
        }
        matrix.asSequence().take(n).flatMap { row -> row.asSequence().take(m).map { value -> if (value) 1 else 0 } }
            .joinToString(separator = "").let(sb::appendLine)
    }

    println(sb)
}