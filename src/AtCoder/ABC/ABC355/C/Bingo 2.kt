package AtCoder.ABC.ABC355.C

fun main() {
    val (N, T) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(N) { BooleanArray(N) }
    fun isDiagonalBingo(r: Int, c: Int): Boolean {
        if (r == c) {
            for (i in 0 until N) {
                if (!matrix[i][i]) return false
            }
            return true
        }
        if (r + c == N - 1) {
            for (i in 0 until N) {
                if (!matrix[i][N - 1 - i]) return false
            }
            return true
        }
        return false
    }

    fun isColBingo(c: Int): Boolean {
        for (nr in 0 until N) {
            if (!matrix[nr][c]) return false
        }
        return true
    }

    fun isRowBingo(r: Int): Boolean {
        for (nc in 0 until N) {
            if (!matrix[r][nc]) return false
        }
        return true
    }

    fun isBingo(r: Int, c: Int): Boolean = isRowBingo(r) || isColBingo(c) || isDiagonalBingo(r, c)
    fun result(): Int {
        for (i in 0 until T) {
            val a = A[i] - 1
            val r = a / N
            val c = a % N
            matrix[r][c] = true
            if(isBingo(r, c)) return i + 1
        }
        return -1
    }
    println(result())
}