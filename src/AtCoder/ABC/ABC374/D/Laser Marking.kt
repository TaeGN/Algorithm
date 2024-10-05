package AtCoder.ABC.ABC374.D

import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    val (N, S, T) = readln().trim().split(" ").map(String::toInt)
    val lines = List(N) { readln().trim().split(" ").map(String::toInt) }
    fun dist(r1: Int, c1: Int, r2: Int, c2: Int): Double =
        sqrt((abs(r1 - r2).let { it * it } + abs(c1 - c2).let { it * it }).toDouble())

    var result = Double.MAX_VALUE
    fun result(arr: IntArray) {
        var pr = 0
        var pc = 0
        var curResult = 0.0
        for (i in arr) {
            val idx = if (i >= N) i - N else i
            curResult += dist(lines[idx][0], lines[idx][1], lines[idx][2], lines[idx][3]) / T
            if (i >= N) {
                curResult += dist(pr, pc, lines[idx][2], lines[idx][3]) / S
                pr = lines[idx][0]
                pc = lines[idx][1]
            } else {
                curResult += dist(pr, pc, lines[idx][0], lines[idx][1]) / S
                pr = lines[idx][2]
                pc = lines[idx][3]
            }
        }
        result = minOf(result, curResult)
    }

    fun permutation(idx: Int = 0, flag: Int = 0, arr: IntArray = IntArray(N)) {
        if (idx == N) {
            result(arr)
            return
        }
        for (i in 0 until N) {
            if ((flag and (1 shl i)) != 0) continue
            arr[idx] = i
            permutation(idx + 1, flag or (1 shl i), arr)
            arr[idx] = N + i
            permutation(idx + 1, flag or (1 shl i), arr)
        }
    }
    permutation()
    println(result)
}