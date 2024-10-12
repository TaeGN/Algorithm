package AtCoder.ABC.ABC375.C

val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() {
    val N = readln().toInt()
    val matrix = Array(N) { readln().trim().toCharArray() }
    val nMatrix = Array(N) { CharArray(N) }
    for (i in 0 until (N / 2)) {
        val len = N - i * 2
        var r = i
        var c = i
        for (d in dr.indices) {
            for (j in 0 until (len - 1)) {
                r += dr[d]
                c += dc[d]
                nMatrix[r][c] = when (i % 4) {
                    0 -> matrix[N - 1 - c][r]
                    1 -> matrix[N - 1 - r][N - 1 - c]
                    2 -> matrix[c][N - 1 - r]
                    3 -> matrix[r][c]
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }
    println(nMatrix.joinToString("\n") { it.joinToString("") })
}