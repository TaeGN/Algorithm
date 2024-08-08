package AtCoder.ABC.ABC362.B

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun dist(x1: Int, y1: Int, x2: Int, y2: Int) = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)

    val x1 = nextInt()
    val y1 = nextInt()
    val x2 = nextInt()
    val y2 = nextInt()
    val x3 = nextInt()
    val y3 = nextInt()
    val a = dist(x1, y1, x2, y2)
    val b = dist(x2, y2, x3, y3)
    val c = dist(x1, y1, x3, y3)
    if (a + b == c || a + c == b || b + c == a) println("Yes")
    else println("No")
}