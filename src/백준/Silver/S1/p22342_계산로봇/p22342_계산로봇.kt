package 백준.Silver.S1.p22342_계산로봇

import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

fun main() {
    val st = StringTokenizer(readln())
    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val weightMatrix = Array(M) { IntArray(N) }
    val outputMatrix = Array(M) { IntArray(N) }
    repeat(M) { r -> readln().forEachIndexed { c, elm -> weightMatrix[r][c] = elm.digitToInt() } }
    for (c in 0 until N) {
        for (r in 0 until M) {
            val prevC = c - 1
            for (prevR in max(0, r - 1)..min(M - 1, r + 1)) {
                outputMatrix[r][c] =
                    max(outputMatrix[r][c], outputMatrix[prevR].getOrElse(prevC) { 0 } + weightMatrix[r][c])
            }
        }
    }
    var result = 0
    for (r in 0 until M) {
        for (c in 0 until N) {
            result = max(result, outputMatrix[r][c] - weightMatrix[r][c])
        }
    }
    println(result)
}