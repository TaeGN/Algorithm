package 백준.Silver.S5.p2303_숫자게임

import java.io.StreamTokenizer
import kotlin.math.max

const val CARD_COUNT = 5

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun IntArray.maxNumber(idx: Int = 0, count: Int = 3, sum: Int = 0): Int =
        if (count == 0) sum % 10
        else if (idx == size) 0
        else max(maxNumber(idx + 1, count, sum), maxNumber(idx + 1, count - 1, sum + this[idx]))

    val numArr = IntArray(CARD_COUNT)
    var maxNumber = 0
    var maxNumberId = 0
    val N = nextInt()
    repeat(N) { id ->
        repeat(CARD_COUNT) { idx ->
            numArr[idx] = nextInt()
        }
        val curMaxNumber = numArr.maxNumber()
        if (maxNumber <= curMaxNumber) {
            maxNumber = curMaxNumber
            maxNumberId = id + 1
        }
    }
    println(maxNumberId)
}