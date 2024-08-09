package AtCoder.ABC.ABC361.A

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val K = nextInt()
    val X = nextInt()
    val arr = IntArray(N)
    repeat(N) { idx ->
        arr[idx] = nextInt()
    }
    val sb = StringBuilder()
    for (i in 0 until N) {
        sb.append("${arr[i]} ")
        if (i == K - 1) sb.append("$X ")
    }
    println(sb)
}