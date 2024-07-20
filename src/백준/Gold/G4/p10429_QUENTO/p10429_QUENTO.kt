package 백준.Gold.G4.p10429_QUENTO

import java.util.StringTokenizer

const val MAP_SIZE = 3
val dr = intArrayOf(1, 0, -1, 0)
val dc = intArrayOf(0, -1, 0, 1)

fun main() = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = List(MAP_SIZE) { CharArray(MAP_SIZE) }
    repeat(MAP_SIZE) { r ->
        for ((c, elm) in readLine().withIndex()) {
            map[r][c] = elm
        }
    }

    fun result(): String {
        val pointList = List(M * 2 - 1) { IntArray(2) }
        val visitedMatrix = List(MAP_SIZE) { BooleanArray(MAP_SIZE) }
        fun isPossible(r: Int, c: Int, idx: Int = 0, sum: Int = map[r][c].digitToInt()): Boolean {
            pointList[idx][0] = r
            pointList[idx][1] = c
            if (idx == pointList.size - 1) return sum == N

            visitedMatrix[r][c] = true
            for (d in dr.indices) {
                val nextR = r + dr[d]
                val nextC = c + dc[d]
                if (nextR in 0 until MAP_SIZE && nextC in 0 until MAP_SIZE && !visitedMatrix[nextR][nextC]) {
                    val newSum =
                        if (idx % 2 == 0) sum else sum + map[nextR][nextC].digitToInt() * (if (map[r][c] == '+') 1 else -1)
                    if (isPossible(nextR, nextC, idx + 1, newSum)) return true
                }
            }

            visitedMatrix[r][c] = false
            return false
        }

        for (r in 0 until MAP_SIZE) {
            for (c in 0 until MAP_SIZE) {
                if (map[r][c].isDigit() && isPossible(r, c)) {
                    return "1\n" + pointList.joinToString("\n") { it.joinToString(" ") }
                }
            }
        }
        return "0"
    }

    println(result())
}
