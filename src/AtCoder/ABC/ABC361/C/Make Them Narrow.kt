package AtCoder.ABC.ABC361.C

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val K = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt()
    }
    numArr.sort()

    var result = Int.MAX_VALUE
    for (i in 0 until K + 1) {
        result = min(result, numArr[i + N - K - 1] - numArr[i])
    }
    println(result)
}