package AtCoder.ABC.ABC352.D

import java.util.TreeSet

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val pArr =
        readln().trim().split(" ").map(String::toInt).mapIndexed { index, i -> index + 1 to i }.sortedBy { it.second }
    val set = TreeSet<Int>()
    var result = Int.MAX_VALUE
    for (i in 0 until N) {
        if (set.size >= K) set.remove(pArr[i - K].first)
        set.add(pArr[i].first)
        if (set.size == K) result = minOf(result, set.last() - set.first())
    }
    println(result)
}