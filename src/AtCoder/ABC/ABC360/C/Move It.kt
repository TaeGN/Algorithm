package AtCoder.ABC.ABC360.C

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val aArr = IntArray(N + 1)
    val wArr = IntArray(N + 1)
    repeat(N) { idx -> aArr[idx + 1] = nextInt() }
    repeat(N) { idx -> wArr[idx + 1] = nextInt() }
    val arr = IntArray(N + 1)
    var result = 0
    for (i in 1..N) {
        if (arr[aArr[i]] == 0) arr[aArr[i]] = wArr[i]
        else if (arr[aArr[i]] > wArr[i]) result += wArr[i]
        else {
            result += arr[aArr[i]]
            arr[aArr[i]] = wArr[i]
        }
    }
    println(result)
}