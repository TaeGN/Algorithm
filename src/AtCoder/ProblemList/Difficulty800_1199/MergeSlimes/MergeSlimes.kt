package AtCoder.ProblemList.Difficulty800_1199.MergeSlimes

import java.util.TreeMap

fun main() {
    val N = readln().trim().toInt()
    val map = TreeMap<Long, Long>()
    repeat(N) {
        val (S, C) = readln().trim().split(" ").map(String::toLong)
        map.compute(S) { _, v -> if (v == null) C else v + C }
    }
    var curS = map.firstKey()
    while (curS != null) {
        var value = curS
        var count = map[curS]!!
        map[curS] = count % 2
        while (count > 1) {
            count /= 2
            value *= 2
            if (count % 2 == 1L) map.compute(value) { _, v -> if (v == null) 1 else v + 1 }
        }
        curS = map.higherKey(curS)
    }
    println(map.values.sum())
}