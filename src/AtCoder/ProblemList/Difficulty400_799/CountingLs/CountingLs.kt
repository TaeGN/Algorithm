package AtCoder.ProblemList.Difficulty400_799.CountingLs

fun main() {
    val N = readln().trim().toInt()
    val matrix = Array(N) { readln().trim().toCharArray() }
    val row = Array(N) { mutableListOf<Int>() }
    val col = Array(N) { mutableListOf<Int>() }
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (matrix[i][j] == 'o') {
                row[i].add(j)
                col[j].add(i)
            }
        }
    }
    var result = 0L
    for (i in 0 until N) {
        for (j in 0 until N) {
            if (matrix[i][j] == 'o') result += (row[i].size - 1).toLong() * (col[j].size - 1)
        }
    }
    println(result)
}