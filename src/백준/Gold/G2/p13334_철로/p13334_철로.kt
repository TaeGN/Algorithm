package 백준.Gold.G2.p13334_철로

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

@JvmInline
value class Left(val value: Int)

@JvmInline
value class Right(val value: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val people = hashMapOf<Left, MutableList<Right>>()
    repeat(n) {
        val st = StringTokenizer(readLine())
        val value1 = st.nextToken().toInt()
        val value2 = st.nextToken().toInt()
        val left = Left(min(value1, value2))
        val right = Right(max(value1, value2))
        if (!people.containsKey(left)) people[left] = mutableListOf()
        people[left]?.add(right)
    }

    val d = readLine().toInt()
    for ((left, rightList) in people.entries) {
        people[left] = rightList.filter { it.value - left.value <= d }.toMutableList()
        people[left]?.sortedBy { it.value }
    }

    var maxCount = 0
    val positionMapList = people.entries.toList().sortedBy { it.key.value }
    val leftList = positionMapList.map { it.key }
    var prevEnd = -100_000_000
    var prevIdx = 0
    fun getCount(idx: Int, end: Int): Int {
        var curIdx = idx
        var count = 0
        while (curIdx < positionMapList.size && positionMapList[curIdx].key.value <= end) {
            count += positionMapList[curIdx].value.binarySearch(end)
            curIdx++
        }
        return count
    }
    for (idx in positionMapList.indices) {
        val start = positionMapList[idx].key.value
        val end = start + d
        val count = -getCount(prevIdx, start - 1) + getCount(leftList.binarySearch(prevEnd), end)
        prevEnd = end
        prevIdx = idx
        maxCount = max(maxCount, count)
    }

    println(maxCount)
}

@JvmName("leftBinarySearch")
fun List<Left>.binarySearch(value: Int): Int =  -(1 + this.binarySearch { it.value.compareTo(value + 0.1) })
@JvmName("rightBinarySearch")
fun List<Right>.binarySearch(value: Int): Int =  -(1 + this.binarySearch { it.value.compareTo(value + 0.1) })
