package AtCoder.ABC.ABC364.C

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextLong(): Long {
        nextToken()
        return nval.toLong()
    }

    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val X = nextLong()
    val Y = nextLong()
    val xArr = IntArray(N)
    val yArr = IntArray(N)
    repeat(N) { idx ->
        xArr[idx] = nextInt()
    }
    repeat(N) { idx ->
        yArr[idx] = nextInt()
    }
    xArr.sortDescending()
    yArr.sortDescending()
    fun result(): Int {
        var sumX = 0L
        var sumY = 0L
        for (i in 0 until N) {
            sumX += xArr[i]
            sumY += yArr[i]
            if (sumX > X || sumY > Y) return i + 1
        }
        return N
    }
    println(result())
}