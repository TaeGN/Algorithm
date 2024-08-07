package 백준.Gold.G5.p30470_호반우가학교에지각한이유3

import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val hList = mutableListOf<Int>()
    val magicList = mutableListOf(0 to 0)
    var maxH = 0
    val N = nextInt()
    repeat(N) {
        if (nextInt() == 1) hList.add(nextInt().also { maxH = max(maxH, it) })
        else magicList.add(hList.size to max(maxH - nextInt(), 0).also { maxH = it })
    }
    var result = 0L
    var toIndex = hList.size
    var minMaxH = Int.MAX_VALUE
    for ((fromIdx, curMaxH) in magicList.reversed()) {
        for (i in fromIdx until toIndex) {
            result += min(minMaxH, hList[i])
        }
        minMaxH = min(minMaxH, curMaxH)
        toIndex = fromIdx
    }
    println(result)
}