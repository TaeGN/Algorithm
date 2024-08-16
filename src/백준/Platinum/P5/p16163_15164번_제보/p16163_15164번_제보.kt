package 백준.Platinum.P5.p16163_15164번_제보

import kotlin.math.min

fun main() {
    val S = readln().asSequence().joinToString("#", "#", "#")
    val A = IntArray(S.length)
    var p = 0
    var r = 0
    for (i in S.indices) {
        A[i] = if (i <= r) min(A[2 * p - i], r - i) else 0
        while (i - A[i] - 1 >= 0 && i + A[i] + 1 < S.length && S[i - A[i] - 1] == S[i + A[i] + 1]) A[i]++
        if (r < i + A[i]) {
            r = i + A[i]
            p = i
        }
    }
    println(A.sumOf { (it + 1).toLong() / 2 })
}