package 백준.S2.p5212_지구온난화

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val GROUND = 'X'
const val OCEAN = '.'
val dr = arrayOf(0, 1, 0, -1)
val dc = arrayOf(1, 0, -1, 0)
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val map = Array(R) { IntArray(C) }

    fun isValidPosition(r: Int, c: Int) = r >= 0 && c >= 0 && r < R && c < C
    fun isNotValidPosition(r: Int, c: Int) = !isValidPosition(r, c)

    for (r in 0 until R) {
        val rowMap = readLine().toCharArray()
        for (c in 0 until C) {
            if (rowMap[c] == OCEAN) {
                map[r][c] = 3
                for (d in dr.indices) {
                    val nextR = r + dr[d]
                    val nextC = c + dc[d]
                    if (isValidPosition(nextR, nextC)) {
                        map[nextR][nextC]++
                    }
                }
            } else {
                for (d in dr.indices) {
                    val nextR = r + dr[d]
                    val nextC = c + dc[d]
                    if (isNotValidPosition(nextR, nextC)) {
                        map[r][c]++
                    }
                }
            }
        }
    }

    var minR = R - 1
    var minC = C - 1
    var maxR = 0
    var maxC = 0
    for (r in 0 until R) {
        for (c in 0 until C) {
            if (map[r][c] < 3) {
                minR = r.coerceAtMost(minR)
                maxR = r.coerceAtLeast(maxR)
                minC = c.coerceAtMost(minC)
                maxC = c.coerceAtLeast(maxC)
            }
        }
    }

    val sb = StringBuilder()
    for (r in minR..maxR) {
        for (c in minC..maxC) {
            sb.append(if (map[r][c] < 3) GROUND else OCEAN)
        }
        sb.append("\n")
    }

    println(sb)
}