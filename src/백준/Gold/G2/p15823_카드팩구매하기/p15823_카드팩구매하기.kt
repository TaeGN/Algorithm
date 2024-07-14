package 백준.Gold.G2.p15823_카드팩구매하기

import java.io.StreamTokenizer

const val MAX_ID = 500_000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val cardArr = IntArray(N)
    repeat(N) { idx ->
        cardArr[idx] = nextInt()
    }

    val cardCountArr = IntArray(MAX_ID + 1)
    fun isPossible(amount: Int): Boolean {
        cardCountArr.fill(0)

        var totalCount = 0
        var selectableTime = 0
        var impossibleCardCount = 0
        for (i in 0 until N) {
            if (++cardCountArr[cardArr[i]] == 2) impossibleCardCount++
            if (i >= amount && --cardCountArr[cardArr[i - amount]] == 1) impossibleCardCount--
            if (i >= amount - 1 && --selectableTime <= 0 && impossibleCardCount == 0) {
                selectableTime = amount
                totalCount++
            }
        }

        return totalCount >= M
    }

    fun maxAmount(start: Int = 1, end: Int = N / M): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) maxAmount(mid, end)
        else maxAmount(start, mid - 1)
    }

    println(maxAmount())
}