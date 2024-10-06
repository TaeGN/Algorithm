package Codeforces.Div2.Round977.A

import java.util.PriorityQueue

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val pq = PriorityQueue<Int>()
        pq.addAll(readln().trim().split(" ").map(String::toInt))
        repeat(N - 1) {
            pq.add((pq.poll() + pq.poll()) / 2)
        }
        sb.appendLine(pq.poll())
    }
    println(sb)
}