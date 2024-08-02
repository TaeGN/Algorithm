package 백준.Silver.S5.p16815_StarInParentheses

import kotlin.math.max

fun main() {
    val str = readln()
    val idx = str.indexOf('*')
    fun String.bracketCount(fromIndex: Int, toIndex: Int = length): Int {
        var count = 0
        for (i in fromIndex until toIndex) {
            if (this[i] == '(') count++
            else count--
        }
        return count
    }
    println(max(str.bracketCount(0, idx), -str.bracketCount(idx + 1)))
}