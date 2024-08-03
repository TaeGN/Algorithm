package 백준.Gold.G3.p7573_고기잡이

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val I = nextInt()
    val M = nextInt()
    val fishArr = IntArray(M)
    repeat(M) { idx ->
        fishArr[idx] = (nextInt() - 1) * N + nextInt() - 1
    }
    var maxCount = 0
    for (i in 0 until M) {
        val fishR = fishArr[i] / N + 1
        val fishC = fishArr[i] % N + 1
        for (rLen in 1 until I / 2) {
            val cLen = (I / 2) - rLen
            for (sr in max(1, fishR - rLen)..fishR) {
                val er = sr + rLen
                if (er > N) continue
                for (sc in max(1, fishC - cLen)..fishC) {
                    val ec = sc + cLen
                    if (ec > N) continue
                    var count = 0
                    for (fish in fishArr) {
                        val r = fish / N + 1
                        val c = fish % N + 1
                        if (r in sr..er && c in sc..ec) count++
                    }
                    maxCount = max(maxCount, count)
                }
            }
        }
    }
    println(maxCount)
}