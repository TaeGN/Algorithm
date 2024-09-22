package 백준.Platinum.P5.p10165_버스노선

import java.util.TreeMap


fun main() {
    val N = readln().toInt()
    val M = readln().toInt()
    val mArr = Array(M) { idx ->
        readln().split(" ").map(String::toInt).let { Triple(idx + 1, it[0], it[1]) }
    }.sortedBy { -((N + it.third - it.second - 1) % N + 1) }
    val map = TreeMap<Int, Int>()
    fun contains(a: Int, b: Int): Boolean {
        if (a > b) {
            map.floorEntry(a)?.let { (_, pb) -> if (b + N <= pb) return true }
        } else {
            map.floorEntry(a)?.let { (_, pb) -> if (b <= pb) return true }
            map.floorEntry(a + N)?.let { (_, pb) -> if (b + N <= pb) return true }
        }
        return false
    }

    fun put(a: Int, b: Int) {
        if (a > b) map[a] = b + N
        else map[a] = b
    }

    val list = mutableListOf<Int>()
    for ((idx, a, b) in mArr) {
        if (contains(a, b)) continue
        list.add(idx)
        put(a, b)
    }
    list.sort()
    println(list.joinToString(" "))
}