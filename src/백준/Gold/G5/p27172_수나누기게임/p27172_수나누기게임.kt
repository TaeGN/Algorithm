package 백준.Gold.G5.p27172_수나누기게임

import java.io.StreamTokenizer

const val MAX_NUMBER = 1_000_000
const val EMPTY = Int.MIN_VALUE
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val scoreArr = IntArray(MAX_NUMBER + 1) { EMPTY }
    val numArr = IntArray(n)
    repeat(n) {idx ->
        val num = readInt()
        numArr[idx] = num
        scoreArr[num] = 0
    }

    for(numA in numArr) {
        for(numB in (numA * 2)..MAX_NUMBER step numA) {
            if(scoreArr[numB] == EMPTY) continue
            scoreArr[numA]++
            scoreArr[numB]--
        }
    }

    numArr.map { scoreArr[it] }.joinToString(" ").let(::println)
}