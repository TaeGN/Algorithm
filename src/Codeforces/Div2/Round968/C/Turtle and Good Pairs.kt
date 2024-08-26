package Codeforces.Div2.Round968.C

import java.util.PriorityQueue

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        val N = readln().toInt()
        val S = readln()
        val countArr = IntArray(26)
        for (c in S) {
            countArr[c - 'a']++
        }
        val pq = PriorityQueue<Pair<Int, Char>>(compareBy { -it.first })
        for ((idx, count) in countArr.withIndex()) {
            if (count > 0) pq.add(count to ('a' + idx))
        }
        while (pq.isNotEmpty()) {
            val (count, c) = pq.poll()
            if (sb.isNotEmpty() && sb.last() == c && pq.isNotEmpty()) {
                val (count2, c2) = pq.poll()
                sb.append(c2)
                if (count2 > 1) pq.add((count2 - 1) to c2)
                pq.add(count to c)
            } else {
                sb.append(c)
                if (count > 1) pq.add((count - 1) to c)
            }
        }
        sb.appendLine()
    }
    println(sb)
}