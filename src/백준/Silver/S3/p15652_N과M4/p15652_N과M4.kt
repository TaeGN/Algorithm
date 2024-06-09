package 백준.Silver.S3.p15652_N과M4

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val sb = StringBuilder()
    fun duplicateCombination(count: Int = 0, lastNum: Int = 1, selected: IntArray = IntArray(m)) {
        if(count == m) {
            sb.appendLine(selected.joinToString(separator = " "))
            return
        }

        for(num in lastNum..n) {
            selected[count] = num
            duplicateCombination(count + 1, num, selected)
        }
    }

    duplicateCombination()
    println(sb)
}