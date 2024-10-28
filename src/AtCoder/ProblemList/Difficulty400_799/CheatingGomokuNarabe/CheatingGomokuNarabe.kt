package AtCoder.ProblemList.Difficulty400_799.CheatingGomokuNarabe

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (H, W, K) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().toCharArray() }
    var result = IMPOSSIBLE
    val count = IntArray(2)
    for (i in 0 until H) {
        count.fill(0)
        for (j in 0 until W) {
            if (matrix[i][j] == 'x') count[0]++
            else if (matrix[i][j] == '.') count[1]++
            if (j >= K) {
                if (matrix[i][j - K] == 'x') count[0]--
                else if (matrix[i][j - K] == '.') count[1]--
            }
            if (j >= K - 1 && count[0] == 0) result = minOf(result, count[1])
        }
    }
    for (j in 0 until W) {
        count.fill(0)
        for (i in 0 until H) {
            if (matrix[i][j] == 'x') count[0]++
            else if (matrix[i][j] == '.') count[1]++
            if (i >= K) {
                if (matrix[i - K][j] == 'x') count[0]--
                else if (matrix[i - K][j] == '.') count[1]--
            }
            if (i >= K - 1 && count[0] == 0) result = minOf(result, count[1])
        }
    }
    println(if (result == IMPOSSIBLE) -1 else result)
}