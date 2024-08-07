package AtCoder.ABC.ABC363.B

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val T = nextInt()
    val P = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt()
    }
    numArr.sortDescending()
    println(max(0, T - numArr[P - 1]))
}