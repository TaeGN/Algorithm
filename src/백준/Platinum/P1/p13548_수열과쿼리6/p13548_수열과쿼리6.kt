package 백준.Platinum.P1.p13548_수열과쿼리6

import kotlin.math.sqrt

fun main() {
    val N = readln().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val M = readln().toInt()
    val Q = Array(M) { idx -> readln().trim().split(" ").map(String::toInt).let { Triple(idx, it[0] - 1, it[1] - 1) } }
    val sqrtN = sqrt(N.toDouble()).toInt()
    Q.sortWith(compareBy({ it.second / sqrtN }, { it.third }))
    val result = IntArray(M)
    val countArr = IntArray(100001)
    val countCountArr = IntArray(100001).apply { this[0] = N }
    var max = 0
    var pl = 0
    var pr = -1
    for ((idx, l, r) in Q) {
        if (pl > l) {
            for (i in l until pl) {
                if (countArr[A[i]] == max) max++
                countCountArr[countArr[A[i]]]--
                countCountArr[++countArr[A[i]]]++
            }
        }
        if (pr < r) {
            for (i in (pr + 1)..r) {
                if (countArr[A[i]] == max) max++
                countCountArr[countArr[A[i]]]--
                countCountArr[++countArr[A[i]]]++
            }
        }
        if (pl < l) {
            for (i in pl until l) {
                if (--countCountArr[countArr[A[i]]] == 0 && countArr[A[i]] == max) max--
                countCountArr[--countArr[A[i]]]++
            }
        }
        if (pr > r) {
            for (i in (r + 1)..pr) {
                if (--countCountArr[countArr[A[i]]] == 0 && countArr[A[i]] == max) max--
                countCountArr[--countArr[A[i]]]++
            }
        }
        pl = l
        pr = r
        result[idx] = max
    }
    println(result.joinToString("\n"))
}