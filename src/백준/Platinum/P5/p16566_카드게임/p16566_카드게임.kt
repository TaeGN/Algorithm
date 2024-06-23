package 백준.Platinum.P5.p16566_카드게임

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val K = readInt()
    val cards = IntArray(M)
    repeat(M) { idx ->
        cards[idx] = readInt()
    }
    cards.sort()

    val cardIdxArr = IntArray(M) { it }
    fun IntArray.getCardIdx(idx: Int): Int = if (this[idx] == idx) idx
    else getCardIdx(this[idx]).also { this[idx] = it }

    fun IntArray.higher(target: Int): Int {
        val idx = binarySearch(target + 1).let { if (it >= 0) it else -(it + 1) }
        return this[if (idx == size) higher(0)
        else cardIdxArr.getCardIdx(idx).also { cardIdxArr[it] = it + 1 }]
    }

    val sb = StringBuilder()
    repeat(K) {
        sb.appendLine(cards.higher(readInt()))
    }
    println(sb)
}