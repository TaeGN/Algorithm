package Codeforces.Div2.EducationalCodeforces.Round170.C

import java.util.TreeMap

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (N, K) = readln().trim().split(" ").map(String::toInt)
        val map = TreeMap(readln().trim().split(" ").map(String::toInt).groupingBy { it }.eachCount())
        val queue = ArrayDeque<Pair<Int, Int>>()
        var result = 0
        var curResult = 0
        for ((a, count) in map) {
            if (queue.isNotEmpty() && (queue.last().first + 1) != a) {
                queue.clear()
                curResult = 0
            }
            queue.add(a to count)
            curResult += count
            if (queue.size > K) curResult -= queue.removeFirst().second
            result = maxOf(result, curResult)
        }
        sb.appendLine(result)
    }
    println(sb)
}