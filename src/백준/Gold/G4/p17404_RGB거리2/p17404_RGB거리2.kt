package 백준.Gold.G4.p17404_RGB거리2

import java.io.StreamTokenizer
import kotlin.math.min

const val MAX_PRICE = 1_000_000

enum class Color { RED, GREEN, BLUE }

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    val readInt = {
        nextToken()
        nval.toInt()
    }

    val n = readInt()
    val priceList = List(n) { arrayOf(0, 0, 0) }
    repeat(n) { idx ->
        priceList[idx][Color.RED.ordinal] = readInt()
        priceList[idx][Color.GREEN.ordinal] = readInt()
        priceList[idx][Color.BLUE.ordinal] = readInt()
    }

    var result = MAX_PRICE
    for (color in Color.entries) {
        val score = Array(3) { MAX_PRICE }
        score[color.ordinal] = priceList[0][color.ordinal]
        repeat(n - 1) { idx ->
            val redScore =
                priceList[idx + 1][Color.RED.ordinal] + min(score[Color.GREEN.ordinal], score[Color.BLUE.ordinal])
            val greenScore =
                priceList[idx + 1][Color.GREEN.ordinal] + min(score[Color.RED.ordinal], score[Color.BLUE.ordinal])
            val blueScore =
                priceList[idx + 1][Color.BLUE.ordinal] + min(score[Color.GREEN.ordinal], score[Color.RED.ordinal])
            score[Color.RED.ordinal] = redScore
            score[Color.GREEN.ordinal] = greenScore
            score[Color.BLUE.ordinal] = blueScore
        }

        for (lastColor in Color.entries.filter { it != color }) {
            result = min(result, score[lastColor.ordinal])
        }
    }

    println(result)
}