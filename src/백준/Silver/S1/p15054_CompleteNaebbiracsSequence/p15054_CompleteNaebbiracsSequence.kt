package 백준.Silver.S1.p15054_CompleteNaebbiracsSequence

import java.io.StreamTokenizer

const val EMPTY = -1
const val IMPOSSIBLE = "*"

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val K = nextInt()
    val N = nextInt()
    val countArr = IntArray(K + 1)
    repeat(N) {
        countArr[nextInt()]++
    }

    fun result(): String {
        if (N % K > 1 && N % K != K - 1) return IMPOSSIBLE
        val count = (if (N % K == K - 1) 1 else 0) + N / K
        var minus = EMPTY
        var plus = EMPTY
        for (i in 1..K) {
            when (countArr[i]) {
                count -> continue
                count - 1 -> if (plus != EMPTY) return IMPOSSIBLE else plus = i
                count + 1 -> if (minus != EMPTY) return IMPOSSIBLE else minus = i
                else -> return IMPOSSIBLE
            }
        }
        return "${if (minus != EMPTY) "-$minus " else ""}${if (plus != EMPTY) "+$plus" else ""}"
    }
    println(result())
}