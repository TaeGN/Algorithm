package 백준.Gold.G4.p24885_주식

import java.io.StreamTokenizer
import kotlin.math.max

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val M = nextInt()
    val K = nextInt()
    val priceArr = IntArray(N)
    repeat(N) { idx ->
        priceArr[idx] = nextInt()
    }

    fun IntArray.maxMoney(idx: Int = 0, money: Long = M.toLong(), loan: Long = 0, stockAmount: Long = 0): Long {
        if (idx == N) return if (stockAmount == 0L) money else 0
        var maxMoney = maxMoney(idx + 1, money, loan, stockAmount)  // 아무것도 안함
        if (stockAmount > 0) {  // 매도
            maxMoney = max(maxMoney, maxMoney(idx, money + stockAmount * this[idx], loan, 0))
        }
        if ((money - loan) * (K + 1) >= this[idx]) { // 매수
            val newMoney = (money - loan) * (K + 1)
            val newLoan = (money - loan) * K
            maxMoney = max(maxMoney, maxMoney(idx + 1, newMoney % this[idx], newLoan, newMoney / this[idx]))
        }
        return maxMoney
    }

    println(priceArr.maxMoney())
}