package 백준.Platinum.P2.p8462_배열의힘

import kotlin.math.sqrt

fun main() {
    val (N, T) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val Q = Array(T) { idx -> readln().trim().split(" ").map { it.toInt() - 1 }.let { Triple(idx, it[0], it[1]) } }
    val sqrtN = sqrt(N.toDouble()).toInt()
    Q.sortWith(compareBy({ it.second / sqrtN }, { it.third }))
    var pl = 0
    var pr = -1
    val result = LongArray(T)
    val countArr = IntArray(1000001)
    var curResult = 0L
    for ((idx, l, r) in Q) {
        if (pl > l) {
            for (i in l until pl) {
                curResult += (2L * countArr[A[i]]++ + 1) * A[i]
            }
        }
        if (pr < r) {
            for (i in (pr + 1)..r) {
                curResult += (2L * countArr[A[i]]++ + 1) * A[i]
            }
        }
        if (pl < l) {
            for (i in pl until l) {
                curResult += (-2L * countArr[A[i]]-- + 1) * A[i]
            }
        }
        if (pr > r) {
            for (i in (r + 1)..pr) {
                curResult += (-2L * countArr[A[i]]-- + 1) * A[i]
            }
        }
        pl = l
        pr = r
        result[idx] = curResult
    }
    println(result.joinToString("\n"))
}