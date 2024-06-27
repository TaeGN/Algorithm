package 백준.Platinum.P5.p1019_책페이지

import kotlin.math.pow

fun main() {
    val countArr = IntArray(10)
    countArr[1] = 1
    for (i in 2 until countArr.size) {
        countArr[i] = 10 * countArr[i - 1] + 10.0.pow(i - 1).toInt()
    }

    val n = readln()
    val totalCountArr = IntArray(10)
    repeat(n.length) { idx ->
        val num = n[idx].digitToInt()
        totalCountArr[num] += n.substring(idx + 1).ifEmpty { "0" }.toInt() + 1
        for (i in 0 until num) {
            if (idx == 0 && i == 0) continue
            totalCountArr[i] += 10.0.pow(n.length - idx - 1).toInt()
        }
        for (i in 0..9) {
            totalCountArr[i] += num * countArr[n.length - idx - 1]
        }
        if (idx == 0) totalCountArr[0] -= "1".repeat(n.length - 1).ifEmpty { "0" }.toInt()
    }

    println(totalCountArr.joinToString(" "))
}