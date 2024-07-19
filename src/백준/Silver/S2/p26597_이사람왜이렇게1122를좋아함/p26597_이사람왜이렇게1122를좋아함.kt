package 백준.Silver.S2.p26597_이사람왜이렇게1122를좋아함

import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

const val MAX_VALUE: Long = 1_000_000_000_000_000_000
const val MIN_VALUE: Long = -MAX_VALUE
const val EMPTY = -1

fun main() = with(System.`in`.bufferedReader()) {
    val Q = readLine().toInt()
    var max = MAX_VALUE
    var min = MIN_VALUE
    var paradoxIdx = EMPTY
    var iGotItIdx = EMPTY
    repeat(Q) { idx ->
        val st = StringTokenizer(readLine())
        val x = st.nextToken().toLong()
        if (st.nextToken() == "^") min = max(min, x + 1)
        else max = min(max, x - 1)
        if (iGotItIdx == EMPTY && min == max) iGotItIdx = idx + 1
        else if (paradoxIdx == EMPTY && (min > max || min < MIN_VALUE || max > MAX_VALUE)) paradoxIdx = idx + 1
    }
    if (paradoxIdx != EMPTY) println("Paradox!\n$paradoxIdx")
    else if (iGotItIdx != EMPTY) println("I got it!\n$iGotItIdx")
    else println("Hmm...")
}