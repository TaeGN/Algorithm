package 백준.Gold.G4.p15722_Segmentation

import java.util.StringTokenizer

fun main() {
    val rList = listOf(-1) + readln().split(" ").map(String::toInt)
    val fList = listOf(-1) + readln().split(" ").map(String::toInt)
    val N = readln().toInt()
    val sb = StringBuilder()
    val recordMap = mutableMapOf<String, IntArray>()
    fun category(name: String, time: Int): String {
        if (name !in recordMap) return "None"
        val (lastTime, f) = recordMap[name]!!
        val rIdx = rList.binarySearch(time - lastTime).let { if (it >= 0) it - 1 else -it - 2 }
        val fIdx = fList.binarySearch(f).let { if (it >= 0) it - 1 else -it - 2 }
        return when (rIdx) {
            0 -> when (fIdx) {
                0 -> "New Customer"
                in 1..2 -> "Potential Loyalist"
                3 -> "Loyal Customer"
                else -> "Champion"
            }

            1 -> when (fIdx) {
                0 -> "Promising"
                in 1..2 -> "Potential Loyalist"
                else -> "Loyal Customer"
            }

            2 -> when (fIdx) {
                in 0..1 -> "About to Sleep"
                2 -> "Need Attention"
                else -> "Loyal Customer"
            }

            3 -> when (fIdx) {
                0 -> "Lost"
                1 -> "Hibernating"
                else -> "About to Leave"
            }

            else -> when (fIdx) {
                in 0..1 -> "Lost"
                in 2..3 -> "About to Leave"
                else -> "Can't Lose Them"
            }
        }
    }
    repeat(N) { time ->
        val st = StringTokenizer(readln())
        if (st.nextToken() == "1") recordMap.compute(st.nextToken()) { _, v ->
            v?.apply { this[0] = time; this[1]++ } ?: intArrayOf(time, 1)
        }
        else sb.appendLine(category(st.nextToken(), time))
    }
    println(sb)
}