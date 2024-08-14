package AtCoder.ABC.ABC359.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    var count = 0
    var n1 = nextInt()
    var n2 = nextInt()
    repeat(2 * N - 2) {
        val n3 = nextInt()
        if (n1 == n3) count++
        n1 = n2
        n2 = n3
    }
    println(count)
}