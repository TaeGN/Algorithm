package 백준.Silver.S1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val EMPTY = '.'
const val BOMB = 'O'
const val NOTHING = 0
const val EXPLOSION_TIME = 3
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val map = Array(r) { IntArray(c) }
    for (i in 0 until r) {
        val charArr = readLine().toCharArray()
        for (j in 0 until c) {
            map[i][j] = if (charArr[j] == BOMB) EXPLOSION_TIME else NOTHING
        }
    }

    fun isValid(i: Int, j: Int): Boolean {
        return i >= 0 && j >= 0 && i < r && j < c
    }

    fun query(time: Int) {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if(time % 2 == 0 && map[i][j] == NOTHING) map[i][j] = time + EXPLOSION_TIME
                if(time % 2 == 1 && map[i][j] == time) {
                    map[i][j] = NOTHING
                    for (d in dr.indices) {
                        val nextI = i + dr[d]
                        val nextJ = j + dc[d]
                        if (isValid(nextI, nextJ) && map[nextI][nextJ] > time) {
                            map[nextI][nextJ] = NOTHING
                        }
                    }
                }
            }
        }
    }

    for (time in 2..n) {
        query(time)
    }

    val sb = StringBuilder()
    for (i in 0 until r) {
        for (j in 0 until c) {
            sb.append(if(map[i][j] == NOTHING) EMPTY else BOMB)
        }
        sb.append("\n")
    }

    println(sb.toString())
}
