package 백준.Gold.G2.p23022_숙제

import java.util.PriorityQueue

data class Info(var t: Int = 0, var v: Int = 0) : Comparable<Info> {
    override fun compareTo(other: Info): Int = t.compareTo(other.t)
}

fun main() {
    val T = readln().toInt()
    val sb = StringBuilder()
    val arr = Array(100000) { Info() }
    val pq = PriorityQueue<Info>(compareBy { -it.v })
    repeat(T) {
        val (N, S) = readln().split(" ").map(String::toInt)
        readln().split(" ").map(String::toInt).forEachIndexed { index, i -> arr[index].t = i }
        readln().split(" ").map(String::toInt).forEachIndexed { index, i -> arr[index].v = i }
        arr.sort(0, N)
        var idx = 0
        while (idx < N && arr[idx].t <= S) {
            pq.add(arr[idx])
            idx++
        }
        var result = 0L
        var ct = S
        while (pq.isNotEmpty() || idx < N) {
            if (pq.isEmpty()) ct = arr[idx].t
            while (idx < N && arr[idx].t <= ct) {
                pq.add(arr[idx])
                idx++
            }
            val (st, v) = pq.poll()
            result += (ct - st).toLong() * v
            ct++
        }
        sb.appendLine(result)
    }
    println(sb)
}