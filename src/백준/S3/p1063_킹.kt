package 백준.S3

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val CHESS_MAP_SIZE = 8
val direction = hashMapOf("R" to Pair(1, 0), "L" to Pair(-1, 0), "B" to Pair(0, -1), "T" to Pair(0, 1), "RT" to Pair(1, 1), "LT" to Pair(-1, 1), "RB" to Pair(1, -1), "LB" to Pair(-1, -1))
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    fun getR(pos: String) = pos.first() - 'A'
    fun getC(pos: String) = pos.last() - '1'

    val _kingPos = st.nextToken()
    val _rockPos = st.nextToken()
    var curKingPos = ChessPosition(getR(_kingPos), getC(_kingPos))
    var curRockPos = ChessPosition(getR(_rockPos), getC(_rockPos))
    val n = st.nextToken().toInt()

    for (i in 0 until n) {
        val (dr, dc) = direction[readLine()] ?: throw Exception("input error")
        val nextKingPos = ChessPosition(curKingPos.r + dr, curKingPos.c + dc)
        if(nextKingPos.isNotValid()) continue
        if(curRockPos == nextKingPos) {
            val nextRockPos = ChessPosition(curRockPos.r + dr, curRockPos.c + dc)
            if(nextRockPos.isNotValid()) continue
            curRockPos = nextRockPos
        }
        curKingPos = nextKingPos
    }

    fun getPos(pos: ChessPosition) = "${(pos.r + 'A'.code).toChar()}${(pos.c + '1'.code).toChar()}"
    println(getPos(curKingPos))
    println(getPos(curRockPos))
}
data class ChessPosition(val r: Int, val c: Int) {
    fun isValid() = r >= 0 && c >= 0 && r < CHESS_MAP_SIZE && c < CHESS_MAP_SIZE
    fun isNotValid() = !isValid()
}