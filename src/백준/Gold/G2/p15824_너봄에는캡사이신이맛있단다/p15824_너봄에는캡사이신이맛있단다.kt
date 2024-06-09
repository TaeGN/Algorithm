package 백준.Gold.G2.p15824_너봄에는캡사이신이맛있단다

import java.io.BufferedReader
import java.io.InputStreamReader

const val MOD = 1_000_000_007
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val score = readLine().split(" ").asSequence().map(String::toLong).sorted().toList()
    val sumScore = MutableList(n) { 0L }
    repeat(n - 1) { idx ->
        sumScore[n - 2 - idx] += (sumScore[n - 1 - idx] + score[n - 1 - idx] - score[idx]) % MOD
    }

    var result = 0L
    var powerOf2 = 1L
    repeat(n - 1) {idx ->
        result = (result + ((sumScore[idx] * powerOf2) % MOD)) % MOD
        powerOf2 = (powerOf2 * 2) % MOD
    }

    println(result)
}
