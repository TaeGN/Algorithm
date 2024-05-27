package 백준.G4.p9663_NQueen

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val board = List(n) { MutableList(n) { false } }
    println(nQueen(board = board))
}

fun nQueen(r: Int = 0, board: List<MutableList<Boolean>>): Int {
    if (r == board.size) return 1

    var count = 0
    for (c in board.indices) {
        if (isValid(r, c, board)) {
            board[r][c] = true
            count += nQueen(r + 1, board)
            board[r][c] = false
        }
    }

    return count
}

fun isValid(r: Int, c: Int, board: List<MutableList<Boolean>>): Boolean {
    val topLeft = min(r, c)
    val topRight = min(r, board.size - 1 - c)
    for (i in 1..topLeft) {
        if (board[r - i][c - i]) return false
    }
    for (i in 1..topRight) {
        if (board[r - i][c + i]) return false
    }
    for (i in 1..r) {
        if (board[r - i][c]) return false
    }

    return true
}