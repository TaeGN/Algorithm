package AtCoder.ABC.ABC373.B

import kotlin.math.abs

fun main() {
    val S = readln()
    var result = 0
    var idx = S.indexOf('A')
    for (i in 1 until 26) {
        val nIdx = S.indexOf('A' + i)
        result += abs(idx - nIdx)
        idx = nIdx
    }
    println(result)
}