package AtCoder.ABC.ABC384.E

import java.util.PriorityQueue

val dr = intArrayOf(0, 0, 1, -1)
val dc = intArrayOf(1, -1, 0, 0)

fun main() {
    val (H, W, X) = readln().trim().split(" ").map(String::toInt)
    val (P, Q) = readln().trim().split(" ").map(String::toInt).map { it - 1 }
    val matrix = List(H) { readln().trim().split(" ").map(String::toLong) }
    val visited = List(H) { BooleanArray(W) }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { matrix[it.first][it.second] })
    var curPower = matrix[P][Q]
    visited[P][Q] = true
    for (d in dr.indices) {
        val nr = P + dr[d]
        val nc = Q + dc[d]
        if (nr in 0 until H && nc in 0 until W) {
            pq.add(nr to nc)
            visited[nr][nc] = true
        }
    }
    while (pq.isNotEmpty() && pq.peek().let { matrix[it.first][it.second] } < curPower.toDouble() / X) {
        val (r, c) = pq.poll()
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in 0 until H && nc in 0 until W && !visited[nr][nc]) {
                pq.add(nr to nc)
                visited[nr][nc] = true
            }
        }
        curPower += matrix[r][c]
    }
    println(curPower)
}