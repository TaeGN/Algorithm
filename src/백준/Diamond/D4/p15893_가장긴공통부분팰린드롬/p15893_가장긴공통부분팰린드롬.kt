package 백준.Diamond.D4.p15893_가장긴공통부분팰린드롬

import java.util.PriorityQueue
import kotlin.math.min

fun main() {
    val N = readln().toInt()
    val strArr = Array(N) { "" }
    repeat(N) { idx -> strArr[idx] = readln() }
    strArr.sortBy { it.length }
    val pq = PriorityQueue<String>(compareBy { -it.length })
    val S = strArr[0].asSequence().joinToString("#", "#", "#")
    val A = IntArray(S.length)
    var p = 0
    var r = 0
    for (i in S.indices) {
        if (i <= r) A[i] = min(A[2 * p - i], r - i)
        else A[i] = 0
        while (i - A[i] - 1 >= 0 && i + A[i] + 1 < S.length && S[i - A[i] - 1] == S[i + A[i] + 1]) A[i]++
        if (r < i + A[i]) {
            r = i + A[i]
            p = i
        }
        pq.add(S.substring(i - A[i], i + A[i] + 1).replace("#", ""))
    }
    fun result(): Int {
        while (pq.isNotEmpty()) {
            val str = pq.poll()
            if (strArr.all { str in it }) return str.length
            if (str.length > 2) pq.add(str.substring(1, str.length - 1))
        }
        return 0
    }
    println(result())
}