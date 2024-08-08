package AtCoder.ABC.ABC362.C

import java.io.StreamTokenizer
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val list = List(N) { IntArray(2) }
    var sumL = 0L
    var sumR = 0L
    repeat(N) { idx ->
        list[idx][0] = nextInt()
        list[idx][1] = nextInt()
        sumL += list[idx][0]
        sumR += list[idx][1]
    }
    val sb = StringBuilder()
    if (sumL <= 0 && 0 <= sumR) {
        sb.appendLine("Yes")
        var remained = sumR
        var i = 0
        while (i < N && remained > 0) {
            val diff = list[i][1] - list[i][0]
            sb.append("${list[i][1] - min(diff.toLong(), remained)} ")
            remained -= diff
            i++
        }
        while (i < N) {
            sb.append("${list[i][1]} ")
            i++
        }
    } else sb.appendLine("No")
    println(sb)
}