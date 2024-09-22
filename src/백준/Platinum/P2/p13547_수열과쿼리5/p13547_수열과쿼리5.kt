package 백준.Platinum.P2.p13547_수열과쿼리5

import kotlin.math.sqrt

fun main() {
    val N = readln().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val M = readln().toInt()
    val Q = Array(M) { idx -> readln().trim().split(" ").map(String::toInt).let { Triple(idx, it[0] - 1, it[1] - 1) } }
    val sqrtN = sqrt(N.toDouble()).toInt()
    Q.sortWith(compareBy({ it.second / sqrtN }, { it.third }))
    val count = IntArray(A.max() + 1)
    var pl = 0
    var pr = -1
    var numCount = 0
    val result = IntArray(M)
    for ((idx, l, r) in Q) {
        if (pl < l) {
            for (i in pl until l) {
                if (count[A[i]] == 1) numCount--
                count[A[i]]--
            }
        }
        if (pl > l) {
            for (i in l until pl) {
                if (count[A[i]] == 0) numCount++
                count[A[i]]++
            }
        }
        if (pr < r) {
            for (i in (pr + 1)..r) {
                if (count[A[i]] == 0) numCount++
                count[A[i]]++
            }
        }
        if (pr > r) {
            for (i in (r + 1)..pr) {
                if (count[A[i]] == 1) numCount--
                count[A[i]]--
            }
        }
        pl = l
        pr = r
        result[idx] = numCount
    }
    println(result.joinToString("\n"))
}