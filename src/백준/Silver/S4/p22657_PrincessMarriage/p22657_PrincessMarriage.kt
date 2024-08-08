package 백준.Silver.S4.p22657_PrincessMarriage

import java.io.StreamTokenizer
import kotlin.math.min

const val MAX_N = 10000
const val MAX_P = 10
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val sb = StringBuilder()
    var N = nextInt()
    var M = nextInt()
    val distArr = IntArray(MAX_P + 1)
    while (N != 0 || M != 0) {
        distArr.fill(0)
        repeat(N) { idx ->
            val D = nextInt()
            val P = nextInt()
            distArr[P] += D
        }
        var total = distArr.foldIndexed(0) { index, acc, i -> acc + index * i }
        var remainedM = M
        for (P in MAX_P downTo 0) {
            total -= min(remainedM, distArr[P]) * P
            remainedM -= distArr[P]
            if (remainedM <= 0) break
        }
        sb.appendLine(total)
        N = nextInt()
        M = nextInt()
    }
    println(sb)
}