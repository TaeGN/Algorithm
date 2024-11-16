package AtCoder.ABC.ABC380.E

import java.util.TreeMap


fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val map = TreeMap<Int, Int>()
    val idxCount = IntArray(N + 1) { 1 }
    val colorCount = IntArray(N + 1) { 1 }
    for (i in 1..N) {
        map[i] = i
    }
    val sb = StringBuilder()
    repeat(Q) {
        val input = readln().trim().split(" ").map(String::toInt)
        if (input[0] == 1) {
            val (_, x, c) = input
            var curIdx = map.floorKey(x)!!
            val curC = map[curIdx]!!
            while (map.higherEntry(curIdx)?.let { (_, nextC) -> curC == nextC } == true) {
                val nextIdx = map.higherKey(curIdx)!!
                idxCount[curIdx] += idxCount[nextIdx]
                map.remove(nextIdx)
            }
            while (map.lowerEntry(curIdx)?.let { (_, prevC) -> curC == prevC } == true) {
                val prevIdx = map.lowerKey(curIdx)!!
                idxCount[prevIdx] += idxCount[curIdx]
                map.remove(curIdx)
                curIdx = prevIdx
            }
            colorCount[curC] -= idxCount[curIdx]
            colorCount[c] += idxCount[curIdx]
            map[curIdx] = c
        } else {
            val (_, c) = input
            sb.appendLine(colorCount[c])
        }
    }
    println(sb)
}