package AtCoder.ABC.ABC383.B

import kotlin.math.abs

fun main() {
    val (H, W, D) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().toCharArray() }
    val visited = Array(H) { BooleanArray(W) }
    var result = 0
    for (h1 in 0 until H) {
        for (w1 in 0 until W) {
            if (matrix[h1][w1] == '#') continue
            for (h2 in 0 until H) {
                for (w2 in 0 until W) {
                    if (matrix[h2][w2] == '#') continue
                    if (h1 < h2 || (h1 == h2 && w1 < w2)) {
                        visited.forEach { it.fill(false) }
                        for (h in (h1 - D)..(h1 + D)) {
                            for (w in (w1 - D)..(w1 + D)) {
                                if ((abs(h - h1) + abs(w - w1)) <= D && matrix.getOrNull(h)?.getOrNull(w) == '.') {
                                    visited[h][w] = true
                                }
                            }
                        }
                        for (h in (h2 - D)..(h2 + D)) {
                            for (w in (w2 - D)..(w2 + D)) {
                                if ((abs(h - h2) + abs(w - w2)) <= D && matrix.getOrNull(h)?.getOrNull(w) == '.') {
                                    visited[h][w] = true
                                }
                            }
                        }
                        result = maxOf(result, visited.sumOf { row -> row.count { it } })
                    }
                }
            }
        }
    }
    println(result)
}