package 백준.Gold.G3.p2342_DanceDanceRevolution

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.min

const val DIRECTION_COUNT = 5
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun minPower(): Int {
        val minPowerMatrix = List(DIRECTION_COUNT) { IntArray(DIRECTION_COUNT) { IMPOSSIBLE } }
        fun List<IntArray>.min() = this.minOf { it.min() }
        fun Int.distance(direction: Int): Int = when {
            this == direction -> 1
            this == 0 || direction == 0 -> 2
            abs(this - direction) == 2 -> 4
            else -> 3
        }

        fun List<IntArray>.move(direction: Int) {
            for (i in 0 until DIRECTION_COUNT) {
                this[i][direction]++
                this[direction][i]++
            }
            for (i in 0 until DIRECTION_COUNT) {
                if (i == direction) continue
                for (j in 0 until DIRECTION_COUNT) {
                    if (j == direction) continue
                    this[i][direction] = min(this[i][direction], this[i][j] + j.distance(direction))
                    this[direction][j] = min(this[direction][j], this[i][j] + i.distance(direction))
                    this[i][j] = IMPOSSIBLE
                }
            }
        }

        minPowerMatrix[0][0] = 0
        while (true) {
            val direction = readInt().let { if (it == 0) return minPowerMatrix.min() else it }
            minPowerMatrix.move(direction)
        }
    }

    println(minPower())
}