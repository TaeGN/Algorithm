package 백준.Silver.S4.p2331_반복수열

import java.util.StringTokenizer
import kotlin.math.pow

fun main() {
    val st = StringTokenizer(readln())
    val A = st.nextToken()
    val P = st.nextToken().toInt()
    fun String.next(): String = fold(0) { acc, c -> acc + c.digitToInt().toDouble().pow(P).toInt() }.toString()
    val numList = mutableListOf<String>()
    val numSet = mutableSetOf<String>()
    var num = A
    while (numSet.add(num)) {
        numList.add(num)
        num = num.next()
    }
    println(numList.indexOf(num))
}