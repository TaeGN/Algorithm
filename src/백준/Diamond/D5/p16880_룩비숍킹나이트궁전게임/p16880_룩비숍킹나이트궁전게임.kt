package 백준.Diamond.D5.p16880_룩비숍킹나이트궁전게임

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

fun main() {
    fun G(x: Long, y: Long, type: String): Long = when (type) {
        "R" -> ((x / 4) xor (y / 4)) * 4 + ((x % 4) xor (y % 4))
        "B" -> min(x, y)
        "K" -> (min(x, y) % 2 * 2) + abs(x - y) % 2
        "N" -> when (min(x, y) % 3) {
            0L -> 0
            1L -> if (x == y) 0 else 1
            2L -> if (abs(x - y) <= 1) 1 else 2
            else -> throw IllegalArgumentException()
        }

        "P" -> ((x / 3) xor (y / 3)) * 3 + (x + y) % 3
        else -> throw IllegalArgumentException()
    }

    val N = readln().toInt()
    var result = 0L
    repeat(N) {
        val st = StringTokenizer(readln())
        val x = st.nextToken().toLong()
        val y = st.nextToken().toLong()
        val type = st.nextToken()
        result = result xor G(x, y, type)
    }
    println(if (result != 0L) "koosaga" else "cubelover")
}

