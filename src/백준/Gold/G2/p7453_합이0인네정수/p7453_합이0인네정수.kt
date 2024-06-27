package 백준.Gold.G2.p7453_합이0인네정수

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val sum1 = IntArray(n * n)
    val sum2 = IntArray(n * n)
    repeat(n) { idx ->
        val a = readInt()
        val b = readInt()
        val c = readInt()
        val d = readInt()
        repeat(n) { count ->
            sum1[idx * n + count] += a
            sum1[idx + n * count] += b
            sum2[idx * n + count] += c
            sum2[idx + n * count] += d
        }
    }

    sum1.sort()
    sum2.sortDescending()
    var i = 0
    var j = 0
    var count = 0L
    while (i < sum1.size && j < sum2.size) {
        when {
            sum1[i] + sum2[j] > 0 -> j++
            sum1[i] + sum2[j] < 0 -> i++
            sum1[i] + sum2[j] == 0 -> {
                var count1 = 1L
                var count2 = 1L
                while (++i < sum1.size && sum1[i] == sum1[i - 1]) count1++
                while (++j < sum2.size && sum2[j] == sum2[j - 1]) count2++
                count += count1 * count2
            }
        }
    }
    println(count)
}