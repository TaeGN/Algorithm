package 백준.Silver.S5.p6159_CostumeParty

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val S = readInt()
    val cowArr = IntArray(N)
    repeat(N) { idx ->
        cowArr[idx] = readInt()
    }
    cowArr.sort()

    var count = 0
    var j = N - 1
    for(i in 0 until N) {
        while(j > i && cowArr[i] + cowArr[j] > S) j--
        if(j <= i) break
        count += j - i
    }

    println(count)
}