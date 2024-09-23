package 백준.Platinum.P1.p14413_Poklon

import kotlin.math.sqrt

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val nArr = readln().trim().split(" ").map(String::toInt).toIntArray()
    val qArr = Array(Q) { idx -> readln().trim().split(" ").map { it.toInt() - 1 }.let { Triple(idx, it[0], it[1]) } }
    val sqrtN = sqrt(N.toDouble()).toInt()
    qArr.sortWith(compareBy({ it.second / sqrtN }, { it.third }))
    var nIdx = 0
    val nToIdxMap = mutableMapOf<Int, Int>()
    for ((i, n) in nArr.withIndex()) {
        if (n !in nToIdxMap) nToIdxMap[n] = nIdx++
        nArr[i] = nToIdxMap[n]!!
    }
    val countArr = IntArray(N)
    val resultArr = IntArray(Q)
    var result = 0
    var pl = 0
    var pr = -1
    for ((idx, l, r) in qArr) {
        if (pl > l) {
            for (i in l until pl) {
                if (countArr[nArr[i]] == 2) result--
                if (++countArr[nArr[i]] == 2) result++
            }
        }
        if (pr < r) {
            for (i in (pr + 1)..r) {
                if (countArr[nArr[i]] == 2) result--
                if (++countArr[nArr[i]] == 2) result++
            }
        }
        if (pl < l) {
            for (i in pl until l) {
                if (countArr[nArr[i]] == 2) result--
                if (--countArr[nArr[i]] == 2) result++
            }
        }
        if (pr > r) {
            for (i in (r + 1)..pr) {
                if (countArr[nArr[i]] == 2) result--
                if (--countArr[nArr[i]] == 2) result++
            }
        }
        pl = l
        pr = r
        resultArr[idx] = result
    }
    println(resultArr.joinToString("\n"))
}