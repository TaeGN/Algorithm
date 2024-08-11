package AtCoder.ABC.ABC366.D

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val matrix = List(N + 1) { List(N + 1) { IntArray(N + 1) } }
    for (x in 1..N) {
        for (y in 1..N) {
            for (z in 1..N) {
                matrix[x][y][z] =
                    nextInt() + (matrix[x - 1][y][z] + matrix[x][y - 1][z] + matrix[x][y][z - 1]) - (matrix[x - 1][y - 1][z] + matrix[x][y - 1][z - 1] + matrix[x - 1][y][z - 1]) + matrix[x - 1][y - 1][z - 1]
            }
        }
    }
    val sb = StringBuilder()
    val Q = nextInt()
    repeat(Q) {
        val lx = nextInt()
        val rx = nextInt()
        val ly = nextInt()
        val ry = nextInt()
        val lz = nextInt()
        val rz = nextInt()
        val result =
            matrix[rx][ry][rz] - (matrix[lx - 1][ry][rz] + matrix[rx][ly - 1][rz] + matrix[rx][ry][lz - 1]) + (matrix[lx - 1][ly - 1][rz] + matrix[rx][ly - 1][lz - 1] + matrix[lx - 1][ry][lz - 1]) - matrix[lx - 1][ly - 1][lz - 1]
        sb.appendLine(result)
    }
    println(sb)
}