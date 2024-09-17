package 백준.Gold.G2.p1508_레이스

import java.util.TreeSet
import kotlin.math.abs

fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val kList = readln().split(" ").map(String::toInt)
    val sortedKList = kList.sorted()
    fun isPossible(dist: Int): Boolean {
        var prev = sortedKList.first()
        var count = 1
        for (i in 1 until K) {
            val cur = sortedKList[i]
            if (cur - prev >= dist) {
                prev = cur
                count++
            }
        }
        return count >= M
    }

    fun search(start: Int = 0, end: Int = N): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }

    val selected = BooleanArray(K)
    fun result(dist: Int, idx: Int = 0, count: Int = 0, set: TreeSet<Int> = TreeSet()): Boolean {
        if (count == M) return true
        if (idx == K) return false
        val k = kList[idx]
        val higher = set.higher(k)?.let { abs(it - k) >= dist } ?: true
        val lower = set.lower(k)?.let { abs(it - k) >= dist } ?: true
        if (higher && lower) {
            set.add(k)
            selected[idx] = true
            if (result(dist, idx + 1, count + 1, set)) return true
        }
        selected[idx] = false
        if (k in set) set.remove(k)
        if (result(dist, idx + 1, count, set)) return true
        return false
    }
    result(search())
    println(selected.joinToString("") { if (it) "1" else "0" })
}