package 백준.G3.p2473_세용액

import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val acidList = mutableListOf<Int>()
    val alkalineList = mutableListOf<Int>()
    repeat(n) {
        val num = readInt()
        if (num >= 0) acidList.add(num)
        else alkalineList.add(num)
    }

    acidList.sort()
    alkalineList.sort()
    result(acidList, alkalineList).let(::println)
}

fun result(acidList: List<Int>, alkalineList: List<Int>): String {
    if (acidList.isEmpty()) alkalineList.asSequence().take(3).joinToString(separator = " ")
    if (alkalineList.isEmpty()) acidList.asSequence().take(3).joinToString(separator = " ")

    var minSum = Int.MAX_VALUE
    val result = arrayOf(0, 0, 0)
    fun Array<Int>.set(first: Int, second: Int, third: Int) {
        result[0] = first
        result[1] = second
        result[2] = third
    }

    fun setTripleListResult(list: List<Int>) {
        val sum = list.asSequence().take(3).sum().let(::abs)
        if (minSum > sum) {
            minSum = sum
            result.set(
                list[0], list[1], list[2]
            )
        }
    }

    fun setDoubleListResult(idx: Int, doubleSum: Int, doublePair: Pair<Int, Int>, anotherList: List<Int>) {
        if (idx >= anotherList.size || idx < 0) return
        if (minSum > abs(anotherList[idx] + doubleSum)) {
            minSum = abs(anotherList[idx] + doubleSum)
            result.set(
                anotherList[idx],
                doublePair.first,
                doublePair.second
            )
        }
    }

    fun setDoubleListResult(originalList: List<Int>, anotherList: List<Int>) {
        originalList.asSequence().flatMapIndexed { firstIdx: Int, firstVal: Int ->
            originalList.asSequence().filterIndexed { secondIdx, _ -> secondIdx > firstIdx }.map { firstVal to it }
        }.forEach {
            val doubleSum = it.first + it.second
            val bsIdx = anotherList.binarySearch(-doubleSum)

            if (bsIdx >= 0) {
                setDoubleListResult(bsIdx, doubleSum, it, anotherList)
                return@forEach
            }

            for (idx in -(bsIdx + 2)..(-bsIdx)) {
                setDoubleListResult(idx, doubleSum, it, anotherList)
            }
        }
    }

    if (acidList.size >= 3) {
        setTripleListResult(acidList)
    }
    if (alkalineList.size >= 3) {
        setTripleListResult(alkalineList.subList(alkalineList.size - 3, alkalineList.size))
    }
    if (acidList.size >= 2 && alkalineList.isNotEmpty()) {
        setDoubleListResult(originalList = acidList, anotherList = alkalineList)
    }
    if (alkalineList.size >= 2 && acidList.isNotEmpty()) {
        setDoubleListResult(originalList = alkalineList, anotherList = acidList)
    }

    return result.sorted().joinToString(separator = " ")
}