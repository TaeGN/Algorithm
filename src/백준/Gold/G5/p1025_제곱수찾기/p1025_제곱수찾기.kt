package 백준.Gold.G5.p1025_제곱수찾기

import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").let { it[0].toInt() to it[1].toInt() }
    val primeSet = (0..sqrt(10.0.pow(max(N, M))).toInt()).asSequence().map { it * it }.toSet()
    val numStrList = mutableListOf<String>()
    repeat(N) {
        numStrList.add(readLine())
    }

    var maxResult = -1
    for (startR in 0 until N) {
        for (startC in 0 until M) {
            for (stepR in -(N - 1) until N) {
                for (stepC in -(M - 1) until M) {
                    var num = numStrList[startR][startC].digitToInt()
                    if (num in primeSet) maxResult = max(maxResult, num)
                    if (stepR == 0 && stepC == 0) continue
                    var r = startR + stepR
                    var c = startC + stepC
                    while (r in 0 until N && c in 0 until M) {
                        num *= 10
                        num += numStrList[r][c].digitToInt()
                        if (num in primeSet) maxResult = max(maxResult, num)
                        r += stepR
                        c += stepC
                    }
                }

            }
        }
    }

    println(maxResult)
}
