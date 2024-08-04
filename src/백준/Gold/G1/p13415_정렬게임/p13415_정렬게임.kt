package 백준.Gold.G1.p13415_정렬게임

import java.io.StreamTokenizer
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

    var (prevSortedCount, isASC) = queryStack.removeLast().let { abs(it) to (it > 0) }
    val sortedArr = numArr.copyOfRange(0, prevSortedCount)
    sortedArr.sort()
    var forwardIdx = 0
    var backwardIdx = prevSortedCount - 1

    while (queryStack.isNotEmpty()) {
        val (nextSortedCount, nextIsASC) = queryStack.removeLast().let { abs(it) to (it > 0) }
        for (i in (prevSortedCount - 1) downTo nextSortedCount) {
            numArr[i] = if (isASC) sortedArr[backwardIdx--] else sortedArr[forwardIdx++]
        }
        prevSortedCount = nextSortedCount
        isASC = nextIsASC
    }
    println(numArr.joinToString(" "))
}