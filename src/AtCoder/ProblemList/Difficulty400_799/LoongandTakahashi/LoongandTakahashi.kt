package AtCoder.ProblemList.Difficulty400_799.LoongandTakahashi

val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() {
    val N = readln().toInt()
    val matrix = Array(N) { Array(N) { "" } }.apply { this[N / 2][N / 2] = "T" }
    var count = 0
    for (i in 0 until (N / 2)) {
        var r = i
        var c = i
        for (d in dr.indices) {
            repeat(N - 1 - 2 * i) {
                matrix[r][c] = (++count).toString()
                r += dr[d]
                c += dc[d]
            }
        }
    }
    println(matrix.joinToString("\n") { it.joinToString(" ") })
}