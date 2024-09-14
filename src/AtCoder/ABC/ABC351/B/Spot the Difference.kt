package AtCoder.ABC.ABC351.B

fun main() {
    val N = readln().toInt()
    val matrixA = Array(N) { readln().toCharArray() }
    val matrixB = Array(N) { readln().toCharArray() }
    fun result(): String {
        for (i in 0 until N) {
            for (j in 0 until N) {
                if (matrixA[i][j] != matrixB[i][j]) return "${i + 1} ${j + 1}"
            }
        }
        return ""
    }
    println(result())
}