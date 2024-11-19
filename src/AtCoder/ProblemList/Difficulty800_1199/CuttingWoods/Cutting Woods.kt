package AtCoder.ProblemList.Difficulty800_1199.CuttingWoods

import java.util.TreeMap

fun main() {
    val (L, Q) = readln().trim().split(" ").map(String::toInt)
    val map = TreeMap<Int, Int>()
    map[0] = L
    val sb = StringBuilder()
    repeat(Q) {
        val (c, x) = readln().trim().split(" ").map(String::toInt)
        val (sx, ex) = map.lowerEntry(x)
        if (c == 1) {
            map[sx] = x
            map[x] = ex
        } else sb.appendLine(ex - sx)
    }
    println(sb)
}