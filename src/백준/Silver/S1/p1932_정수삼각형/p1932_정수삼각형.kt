package 백준.Silver.S1.p1932_정수삼각형

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val sumArr = IntArray(n)
    repeat(n) {
        val line = br.readLine().split(" ").map(String::toInt)
        for (idx in (line.size - 1) downTo 0) {
            sumArr[idx] = max(sumArr[idx], sumArr.getOrElse(idx - 1) { 0 }) + line[idx]
        }
    }

    println(sumArr.max())
}