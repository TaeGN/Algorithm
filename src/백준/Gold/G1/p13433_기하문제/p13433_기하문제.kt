package 백준.Gold.G1.p13433_기하문제

import kotlin.math.abs
import kotlin.math.sqrt

fun main() {
    val N = readln().toInt()
    val R = readln().trim().split(" ").map(String::toInt)
    fun len(r1: Int, r2: Int) =
        sqrt(((r1 + r2).let { it.toLong() * it } - abs(r1 - r2).let { it.toLong() * it }).toDouble())

    var result = Double.MAX_VALUE
    val posArr = DoubleArray(N)
    fun dfs(idx: Int = 0, flag: Int = 0, arr: IntArray = IntArray(N)) {
        if (idx == N) {
            posArr.fill(0.0)
            for (i in 1 until N) {
                for (j in 0 until i) {
                    posArr[i] = maxOf(posArr[i], posArr[j] + len(R[arr[i]], R[arr[j]]))
                }
            }
            result = minOf(result, posArr.last())
            return
        }
        for (i in 0 until N) {
            if ((flag and (1 shl i)) != 0) continue
            arr[idx] = i
            dfs(idx + 1, flag or (1 shl i), arr)
        }
    }
    dfs()
    println(result)
}