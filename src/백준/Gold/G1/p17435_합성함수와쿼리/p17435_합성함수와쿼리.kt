package 백준.Gold.G1.p17435_합성함수와쿼리

const val SIZE = 19
fun main() {
    val M = readln().toInt()
    val F = Array(SIZE) { IntArray(M) }.apply { this[0] = readln().split(" ").map { it.toInt() - 1 }.toIntArray() }
    for (i in 1 until SIZE) {
        for (j in 0 until M) {
            F[i][j] = F[i - 1][F[i - 1][j]]
        }
    }

    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, X) = readln().split(" ").map(String::toInt)
        var x = X - 1
        for (j in F.indices) {
            if ((N and (1 shl j)) != 0) x = F[j][x]
        }
        sb.appendLine(x + 1)
    }
    println(sb)
}