package 백준.Gold.G3

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(-1, 0, 1, 0)
    val n = readLine().toInt()
    val map = Array(n) { IntArray(n) }
    for (r in 0 until n) {
        val st = StringTokenizer(readLine())
        for (c in 0 until n) {
            map[r][c] = st.nextToken().toInt()
        }
    }

    var result = 0
    fun isInTheSandpit(r: Int, c: Int) = r >= 0 && c >= 0 && r < n && c < n
    fun isNotInTheSandpit(r: Int, c: Int) = !isInTheSandpit(r, c)
    fun moveTornado(r: Int, c: Int, d: Int) {
        var remainingSand = map[r][c]
        fun moveSand(r: Int, c: Int, value: Int) {
            if (isInTheSandpit(r, c)) map[r][c] += value
            else result += value
        }
        fun moveSand(ratio: Int, front: Int = 0, back: Int = 0, right: Int = 0, left: Int = 0) {
            val value = map[r][c] * ratio / 100
            val nextR = r + dr[d] * (front - back) + dr[(d + 3) % dr.size] * right + dr[(d + 1) % dr.size] * left
            val nextC = c + dc[d] * (front - back) + dc[(d + 3) % dc.size] * right + dc[(d + 1) % dc.size] * left
            moveSand(nextR, nextC, value)
            remainingSand -= value
        }

        moveSand(5, front = 2)
        moveSand(10, front = 1, right = 1)
        moveSand(10, front = 1, left = 1)
        moveSand(7, right = 1)
        moveSand(7, left = 1)
        moveSand(2, right = 2)
        moveSand(2, left = 2)
        moveSand(1, back = 1, right = 1)
        moveSand(1, back = 1, left = 1)
        moveSand(r + dr[d], c + dc[d], remainingSand)
        map[r][c] = 0
    }

    fun moveTornadoAll() {
        var r = n / 2
        var c = n / 2
        var d = 0
        var length = 1.0
        while (length <= n) {
            for (i in 1..length.toInt()) {
                r += dr[d]
                c += dc[d]
                if (isNotInTheSandpit(r, c)) break
                moveTornado(r, c, d)
            }
            d = (d + 1) % dr.size
            length += 0.5
        }
    }

    moveTornadoAll()
    println(result)
}
