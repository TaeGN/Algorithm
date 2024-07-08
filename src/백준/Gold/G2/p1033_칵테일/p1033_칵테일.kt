package 백준.Gold.G2.p1033_칵테일

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    infix fun Int.gcd(o: Int): Int = if (this == 0 || o == 0) max(this, o)
    else min(this, o) gcd (max(this, o) % min(this, o))

    val N = readInt()
    val ratioList = mutableListOf<IntArray>()
    repeat(N - 1) {
        val ratio = IntArray(N)
        val a = readInt()
        val b = readInt()
        val p = readInt()
        val q = readInt()
        val gcd = p gcd q
        ratio[a] = p / gcd
        ratio[b] = q / gcd
        ratioList.add(ratio)
    }

    for ((idx, ratio) in ratioList.withIndex()) {
        for (otherIdx in (idx + 1) until ratioList.size) {
            val otherRatio = ratioList[otherIdx]
            for (i in 0 until N) {
                if (ratio[i] == 0 || otherRatio[i] == 0) continue
                val gcd = ratio[i] gcd otherRatio[i]
                val otherRatioMul = ratio[i] / gcd
                val ratioMul = otherRatio[i] / gcd
                for (j in 0 until N) {
                    otherRatio[j] = max(otherRatio[j] * otherRatioMul, ratio[j] * ratioMul)
                }
                break
            }
        }
    }

    println(ratioList.last().joinToString(" "))
}