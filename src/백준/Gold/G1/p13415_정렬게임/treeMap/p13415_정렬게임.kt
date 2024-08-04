package 백준.Gold.G1.p13415_정렬게임.treeMap

import java.io.StreamTokenizer
import java.util.TreeMap
import kotlin.math.abs

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun ArrayDeque<Int>.push(num: Int) {
        while (isNotEmpty() && abs(first()) <= abs(num)) removeFirst()
        addFirst(num)
    }

    fun TreeMap<Int, Int>.pollLast(): Int {
        val (k, v) = lastEntry()
        if (v > 1) this[k] = v - 1
        else pollLastEntry()
        return k
    }

    fun TreeMap<Int, Int>.pollFirst(): Int {
        val (k, v) = firstEntry()
        if (v > 1) this[k] = v - 1
        else pollFirstEntry()
        return k
    }

    val N = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx ->
        numArr[idx] = nextInt()
    }
    val K = nextInt()
    val queryStack = ArrayDeque<Int>()

    repeat(K) {
        queryStack.push(nextInt())
        queryStack.push(-nextInt())
    }
    queryStack.push(0)

    val numMap = TreeMap<Int, Int>()
    var (prevSortedCount, isASC) = queryStack.removeLast().let { abs(it) to (it > 0) }
    repeat(prevSortedCount) { idx ->
        numMap.compute(numArr[idx]) { _, v -> if (v == null) 1 else v + 1 }
    }

    while (queryStack.isNotEmpty()) {
        val (nextSortedCount, nextIsASC) = queryStack.removeLast().let { abs(it) to (it > 0) }
        for (i in (prevSortedCount - 1) downTo nextSortedCount) {
            numArr[i] = if (isASC) numMap.pollLast() else numMap.pollFirst()
        }
        prevSortedCount = nextSortedCount
        isASC = nextIsASC
    }
    println(numArr.joinToString(" "))
}