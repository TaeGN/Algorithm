package AtCoder.ABC.ABC377.C

data class Point(val r: Int, val c: Int)

val dr = intArrayOf(0, 1, 2, 2, 1, -1, -2, -2, -1)
val dc = intArrayOf(0, -2, -1, 1, 2, 2, 1, -1, -2)

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val set = mutableSetOf<Point>()
    repeat(M) {
        val (r, c) = readln().trim().split(" ").map(String::toInt)
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in 1..N && nc in 1..N) set.add(Point(nr, nc))
        }
    }
    println(N.toLong() * N - set.size)
}