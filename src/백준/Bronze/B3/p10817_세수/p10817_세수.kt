package 백준.Bronze.B3.p10817_세수

import java.util.StringTokenizer
import kotlin.math.max

fun main() {
    val st = StringTokenizer(readln())
    var first = 0
    var second = 0
    repeat(3) {
        val num = st.nextToken().toInt()
        if (first < num) {
            second = first
            first = num
        } else {
            second = max(second, num)
        }
    }

    println(second)
}