package 백준.Platinum.P3.p4380_문자열장식

import java.util.PriorityQueue

data class Str(val s: String, val idx: Int = 0) : Comparable<Str> {
    fun first() = this[0]
    fun length() = s.length - idx
    fun hasNext() = idx + 1 < s.length
    operator fun get(i: Int) = s[i + idx]
    override fun compareTo(other: Str): Int {
        for (i in 0 until minOf(length(), other.length())) {
            if (this[i] == other[i]) continue
            return this[i].compareTo(other[i])
        }
        return -length().compareTo(other.length())
    }
}

fun main() {
    val N = readln().toInt()
    val pq = PriorityQueue<Str>()
    repeat(N) { pq.add(Str(readln())) }
    val sb = StringBuilder()
    while (pq.isNotEmpty()) {
        val str = pq.poll()
        sb.append(str.first())
        if (str.hasNext()) pq.add(str.copy(idx = str.idx + 1))
    }
    println(sb)
}