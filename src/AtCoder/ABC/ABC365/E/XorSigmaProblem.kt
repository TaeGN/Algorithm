package AtCoder.ABC.ABC365.E

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = numArr.getOrElse(idx - 1) { 0 } xor nextInt()
    }
    var result = 0L
    val oneCount = IntArray(30)
    repeat(30) { bit ->
        repeat(N) { idx ->
            if (numArr[idx] and (1 shl bit) != 0) oneCount[bit]++
        }
        result += oneCount[bit].toLong() * (N - oneCount[bit]) * (1 shl bit)
    }
    repeat(N - 1) { idx ->
        result += numArr[idx + 1]
        result -= numArr[idx] xor numArr[idx + 1]
    }
    println(result)
}