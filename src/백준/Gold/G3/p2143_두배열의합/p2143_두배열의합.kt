package 백준.Gold.G3.p2143_두배열의합

import java.io.StreamTokenizer
import kotlin.math.max

const val MAX_N = 1_000
const val MAX_M = 1_000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val target = readInt()
    val n = readInt()
    val arr = Array(max(MAX_N, MAX_M) + 1) { 0 }
    val sumMapA = mutableMapOf<Int, Int>()
    fun setMap(count: Int, map: MutableMap<Int, Int>) {
        repeat(count) { j ->
            arr[j + 1] = arr[j] + readInt()
            repeat(j + 1) { i ->
                val sum = arr[j + 1] - arr[i]
                map[sum] = map.getOrDefault(sum, 0) + 1
            }
        }
    }
    setMap(n, sumMapA)
    val m = readInt()
    val sumMapB = mutableMapOf<Int, Int>()
    setMap(m, sumMapB)

    val result = sumMapA.entries.fold(0L) { acc, mutableEntry ->
        acc + mutableEntry.value.toLong() * sumMapB.getOrDefault(
            target - mutableEntry.key,
            0
        )
    }

    println(result)
}