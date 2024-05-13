package 백준.S3

import java.io.BufferedReader
import java.io.InputStreamReader

data class Turtle(var r: Int = 0, var c: Int = 0, var d: Int = 0) {
    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, 1, 0, -1)
    private var minR: Int = 0
    private var minC: Int = 0
    private var maxR: Int = 0
    private var maxC: Int = 0

    fun orderAll(orders: CharArray): Turtle {
        orders.forEach(this::order)
        return this
    }

    private fun order(order: Char) {
        when (order) {
            'F' -> {
                setMinAndMaxValues(r + dr[d], c + dc[d])
                r += dr[d]
                c += dc[d]
            }

            'B' -> {
                setMinAndMaxValues(r - dr[d], c - dc[d])
                r -= dr[d]
                c -= dc[d]
            }

            'L' -> d = (d + 3) % dr.size
            'R' -> d = (d + 1) % dr.size
            else -> throw Exception("input error")
        }
    }

    private fun setMinAndMaxValues(r: Int, c: Int) {
        minR = minR.coerceAtMost(r)
        minC = minC.coerceAtMost(c)
        maxR = maxR.coerceAtLeast(r)
        maxC = maxC.coerceAtLeast(c)
    }

    fun minRectangle() = (maxR - minR) * (maxC - minC)
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val t = readLine().toInt()
    val sb = StringBuilder()
    for (tc in 0 until t) {
        val minRectangle = Turtle().orderAll(readLine().toCharArray()).minRectangle()
        sb.append(minRectangle).append("\n")
    }

    println(sb)
}