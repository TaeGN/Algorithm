package AtCoder.ABC.ABC367.D

import java.util.*

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val sumArr = IntArray(2 * N - 1)
    val st = StringTokenizer(readln())
    repeat(N) { idx -> sumArr[idx] = ((sumArr.getOrElse(idx - 1) { 0 } + st.nextToken().toLong()) % M).toInt() }
    for (i in 0 until N - 1) {
        sumArr[i + N] = ((sumArr[N - 1].toLong() + sumArr[i]) % M).toInt()
    }
    val map = mutableMapOf<Int, MutableList<Int>>()
    for ((idx, sum) in sumArr.withIndex()) {
        map.compute(sum) { _, v -> v ?: mutableListOf() }
        map[sum]?.add(idx)
    }
    var result = 0L
    for (list in map.values) {
        for (i in 0 until list.size - 1) {
            if (list[i] >= N) break
            result += list.binarySearch(list[i] + N).let { if (it >= 0) it else -it - 1 } - i - 1
        }
    }
    println(result)
}