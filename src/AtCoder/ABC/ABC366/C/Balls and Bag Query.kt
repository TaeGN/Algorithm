package AtCoder.ABC.ABC366.C

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val map = mutableMapOf<Int, Int>()
    val Q = nextInt()
    val sb = StringBuilder()
    repeat(Q) {
        when (nextInt()) {
            1 -> map.compute(nextInt()) { _, v -> if (v == null) 1 else v + 1 }
            2 -> map.compute(nextInt()) { _, v -> if (v == null || v == 1) null else v - 1 }
            3 -> sb.appendLine(map.size)
        }
    }
    println(sb)
}