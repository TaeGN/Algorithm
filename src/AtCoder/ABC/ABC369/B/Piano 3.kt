package AtCoder.ABC.ABC369.B

import kotlin.math.abs

fun main() {
    val N = readln().toInt()
    var result = 0
    var l = -1
    var r = -1
    repeat(N) {
        val (A, S) = readln().trim().split(" ").let { it[0].toInt() to it[1] }
        if (S == "L") {
            if (l != -1) result += abs(l - A)
            l = A
        } else {
            if (r != -1) result += abs(r - A)
            r = A
        }
    }
    println(result)
}