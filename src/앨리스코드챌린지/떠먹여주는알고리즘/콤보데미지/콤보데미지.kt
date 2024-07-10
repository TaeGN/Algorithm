package 앨리스코드챌린지.떠먹여주는알고리즘.콤보데미지

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    var totalDamage = 0.0
    repeat(N) { idx ->
        totalDamage += nextInt() * when (idx) {
            in 0 until M -> 1.0
            M -> 0.7
            M + 1 -> 0.5
            M + 2 -> 0.4
            else -> 0.3
        }
    }

    println(String.format("%.1f", totalDamage))
}