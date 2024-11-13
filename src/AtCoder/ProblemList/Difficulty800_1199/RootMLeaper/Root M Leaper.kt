package AtCoder.ProblemList.Difficulty800_1199.RootMLeaper

import kotlin.math.sqrt

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val squareSet = (0..1000).map { it * it }.toSet()
    val list = mutableListOf<Pair<Int, Int>>()
    for (i in 0..1000) {
        val square = i * i
        if ((M - square) in squareSet) {
            val j = sqrt((M - square).toDouble()).toInt()
            list.add(i to j)
            list.add(-i to j)
            list.add(i to -j)
            list.add(-i to -j)
        }
    }
    val matrix = Array(N) { IntArray(N) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(0 to 0)
    var count = 0
    while (queue.isNotEmpty()) {
        count++
        repeat(queue.size) {
            val (x, y) = queue.removeFirst()
            for ((dx, dy) in list) {
                if ((x + dx) in 0 until N && (y + dy) in 0 until N && matrix[x + dx][y + dy] > count) {
                    matrix[x + dx][y + dy] = count
                    queue.add((x + dx) to (y + dy))
                }
            }
        }
    }
    println(matrix.joinToString("\n") { it.joinToString(" ") { i -> if (i == IMPOSSIBLE) "-1" else "$i" } })
}