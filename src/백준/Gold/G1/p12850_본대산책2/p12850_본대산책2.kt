package 백준.Gold.G1.p12850_본대산책2

const val MOD = 1_000_000_007
fun main() {
    operator fun List<List<Int>>.times(other: List<List<Int>>): List<List<Int>> {
        val newMatrix = List(size) { IntArray(size) }
        for ((r, row) in this.withIndex()) {
            for ((c, col) in other.withIndex()) {
                for (idx in indices) {
                    newMatrix[r][c] = ((newMatrix[r][c] + (row[idx].toLong() * col[idx] % MOD)) % MOD).toInt()
                }
            }
        }
        return newMatrix.map(IntArray::toList)
    }

    fun List<List<Int>>.pow(n: Int): List<List<Int>> {
        if (n == 1) return this
        val half = pow(n / 2)
        val halfPow2 = half * half
        return if (n % 2 == 1) this * halfPow2 else halfPow2
    }

    val matrix = listOf(
        listOf(0, 1, 1, 0, 0, 0, 0, 0),
        listOf(1, 0, 1, 1, 0, 0, 0, 0),
        listOf(1, 1, 0, 1, 0, 1, 0, 0),
        listOf(0, 1, 1, 0, 1, 1, 0, 0),
        listOf(0, 0, 0, 1, 0, 1, 1, 0),
        listOf(0, 0, 1, 1, 1, 0, 0, 1),
        listOf(0, 0, 0, 0, 1, 0, 0, 1),
        listOf(0, 0, 0, 0, 0, 1, 1, 0),
    )

    println(matrix.pow(readln().toInt())[0][0])
}