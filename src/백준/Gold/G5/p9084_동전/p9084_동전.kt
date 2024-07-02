package 백준.Gold.G5.p9084_동전

import java.io.StreamTokenizer

const val MAX_N = 20
const val MAX_PRICE = 10000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val tc = readInt()
    val sb = StringBuilder()
    val numArr = IntArray(MAX_N)
    val priceArr = IntArray(MAX_PRICE + 1)
    repeat(tc) {
        val n = readInt()
        repeat(n) { idx ->
            numArr[idx] = readInt()
        }
        val m = readInt()
        priceArr.fill(0, 0, m + 1)
        for (idx in 0 until n) {
            val num = numArr[idx]
            priceArr[num]++
            for (price in num..m) {
                priceArr[price] += priceArr[price - num]
            }
        }

        sb.appendLine(priceArr[m])
    }
    println(sb)
}