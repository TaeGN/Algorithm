package AtCoder.ABC.ABC361.B

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val a = intArrayOf(nextInt(), nextInt(), nextInt())
    val b = intArrayOf(nextInt(), nextInt(), nextInt())
    val c = intArrayOf(nextInt(), nextInt(), nextInt())
    val d = intArrayOf(nextInt(), nextInt(), nextInt())
    var isPossible = true
    for (i in 0 until 3) {
        if (max(min(a[i], b[i]), min(c[i], d[i])) >= min(max(a[i], b[i]), max(c[i], d[i]))) {
            isPossible = false
            break
        }
    }
    println(if (isPossible) "Yes" else "No")
}