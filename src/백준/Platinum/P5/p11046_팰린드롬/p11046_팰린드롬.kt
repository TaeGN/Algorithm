package 백준.Platinum.P5.p11046_팰린드롬

import java.util.StringTokenizer
import kotlin.math.min

fun main() {
    val N = readln().toInt()
    val numArr = IntArray(2 * N + 1)
    var st = StringTokenizer(readln())
    for (i in 0 until N) {
        numArr[i * 2 + 1] = st.nextToken().toInt()
    }
    val A = IntArray(numArr.size)
    var p = 0
    var r = 0
    for (i in numArr.indices) {
        A[i] = if (i <= r) min(A[2 * p - i], r - i) else 0
        while (i - A[i] - 1 >= 0 && i + A[i] + 1 < numArr.size && numArr[i - A[i] - 1] == numArr[i + A[i] + 1]) A[i]++
        if (r < i + A[i]) {
            r = i + A[i]
            p = i
        }
    }
    val M = readln().toInt()
    val sb = StringBuilder()
    repeat(M) {
        st = StringTokenizer(readln())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        val center = start + end - 1
        val length = end - start + 1
        sb.appendLine(if (A[center] >= length) 1 else 0)
    }
    println(sb)
}