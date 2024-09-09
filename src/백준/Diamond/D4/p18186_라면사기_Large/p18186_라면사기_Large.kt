package 백준.Diamond.D4.p18186_라면사기_Large

import java.util.StringTokenizer

fun main() {
    val (N, B, C) = readln().trim().split(" ").map(String::toInt)
    val st = StringTokenizer(readln().trim())
    fun result(): Long {
        var result = 0L
        if (B <= C) {
            repeat(N) { result += st.nextToken().toLong() * B }
        } else {
            val A = intArrayOf(0, st.nextToken().toInt(), st.nextToken().toInt())
            val countArr = IntArray(3)
            val priceArr = intArrayOf(B, B + C, B + 2 * C)
            repeat(N) { idx ->
                A[0] = A[1]
                A[1] = A[2]
                A[2] = if (idx < N - 2) st.nextToken().toInt() else 0
                countArr[2] = A.min()
                countArr[1] = minOf(A[0], A[1]) - countArr[2]
                maxOf(0, minOf(A[1] - countArr[1] - A[2], countArr[2])).let { countArr[1] += it; countArr[2] -= it }
                countArr[0] = A[0] - countArr[1] - countArr[2]
                for (j in 0 until 3) {
                    result += countArr[j].toLong() * priceArr[j]
                }
                A[1] -= countArr[1] + countArr[2]
                A[2] -= countArr[2]
            }
        }
        return result
    }

    println(result())
}