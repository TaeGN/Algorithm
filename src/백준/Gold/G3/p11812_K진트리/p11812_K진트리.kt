package 백준.Gold.G3.p11812_K진트리

import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toLong()
    val K = st.nextToken().toInt()
    val Q = st.nextToken().toInt()
    val sb = StringBuilder()
    fun result(x: Long, y: Long): Long {
        if (K == 1) return abs(x - y)
        var result = 0L
        var px = x
        var py = y
        while (px != py) {
            result++
            if (px < py) py = (py + (K - 2)) / K
            else px = (px + (K - 2)) / K
        }
        return result
    }
    repeat(Q) {
        val (x, y) = readln().trim().split(" ").map(String::toLong)
        sb.appendLine(result(x, y))
    }
    println(sb)
}