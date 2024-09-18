package 백준.Platinum.P4.p3043_장난감탱크

import kotlin.math.abs

fun main() {
    val N = readln().toInt()
    val rArr = IntArray(N)
    val cArr = IntArray(N)
    repeat(N) { idx -> readln().split(" ").map(String::toInt).let { rArr[idx] = it[0] - 1; cArr[idx] = it[1] - 1 } }
    var totalCount = 0
    val uList = mutableListOf<Pair<Int, Int>>()
    val dList = mutableListOf<Pair<Int, Int>>()
    val lList = mutableListOf<Pair<Int, Int>>()
    val rList = mutableListOf<Pair<Int, Int>>()
    val sortedRArr = rArr.asSequence().mapIndexed { index, i -> index to i }.sortedBy { it.second }.toList()
    val sortedCArr = cArr.asSequence().mapIndexed { index, i -> index to i }.sortedBy { it.second }.toList()
    for (i in 0 until N) {
        if (sortedRArr[i].second > i) uList.add(sortedRArr[i].first to abs(sortedRArr[i].second - i))
        else if (sortedRArr[i].second < i) dList.add(sortedRArr[i].first to abs(sortedRArr[i].second - i))
        if (sortedCArr[i].second > i) lList.add(sortedCArr[i].first to abs(sortedCArr[i].second - i))
        else if (sortedCArr[i].second < i) rList.add(sortedCArr[i].first to abs(sortedCArr[i].second - i))
        totalCount += abs(sortedRArr[i].second - i) + abs(sortedCArr[i].second - i)
    }
    val sb = StringBuilder()
    uList.asSequence().sortedBy { rArr[it.first] }.forEach { (idx, count) -> repeat(count) { sb.appendLine("${idx + 1} U") } }
    dList.asSequence().sortedBy { -rArr[it.first] }.forEach { (idx, count) -> repeat(count) { sb.appendLine("${idx + 1} D") } }
    lList.asSequence().sortedBy { cArr[it.first] }.forEach { (idx, count) -> repeat(count) { sb.appendLine("${idx + 1} L") } }
    rList.asSequence().sortedBy { -cArr[it.first] }.forEach { (idx, count) -> repeat(count) { sb.appendLine("${idx + 1} R") } }
    println(totalCount)
    println(sb)
}