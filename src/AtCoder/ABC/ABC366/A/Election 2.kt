package AtCoder.ABC.ABC366.A

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val T = nextInt()
    val A = nextInt()
    println(if (max(T, A) > N / 2) "Yes" else "No")
}