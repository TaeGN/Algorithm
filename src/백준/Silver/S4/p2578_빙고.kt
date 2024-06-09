package 백준.Silver.S4

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

const val BINGO_MAP_ROW = 5
const val BINGO_MAP_COL = 5
const val BINGO_MAP_SIZE = BINGO_MAP_ROW * BINGO_MAP_COL
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numPos = IntArray(BINGO_MAP_SIZE + 1)
    val bingoMap = Array(BINGO_MAP_ROW) { IntArray(BINGO_MAP_COL) { 1 } }
    var bingoCount: Int? = null
    var st: StringTokenizer

    fun init(callback: (Int, Int, Int) -> Unit) {
        for (r in 0 until BINGO_MAP_ROW) {
            st = StringTokenizer(readLine())
            for (c in 0 until BINGO_MAP_COL) {
                callback(r, c, st.nextToken().toInt())
            }
        }
    }

    fun isThreeBingo(): Boolean {
        var result = 0
        var diagonal1 = 0
        var diagonal2 = 0
        for (r in 0 until BINGO_MAP_ROW) {
            var row = 0
            var col = 0
            for (c in 0 until BINGO_MAP_COL) {
                row += bingoMap[r][c]
                col += bingoMap[c][r]
            }
            if(row == 0) result++
            if(col == 0) result++
            diagonal1 += bingoMap[r][r]
            diagonal2 += bingoMap[r][BINGO_MAP_COL - r - 1]
        }
        if(diagonal1 == 0) result++
        if(diagonal2 == 0) result++

        return result >= 3
    }


    init() { r, c, value ->
        numPos[value] = r * BINGO_MAP_ROW + c
    }

    init() { r, c, value ->
        bingoMap[numPos[value] / BINGO_MAP_ROW][numPos[value] % BINGO_MAP_ROW] = 0
        if(bingoCount == null && isThreeBingo()) bingoCount = r * BINGO_MAP_ROW + c + 1
    }

    println(bingoCount)
}