package 백준.Gold.G5.p10975_데크소트2

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val numArr = IntArray(N)
    repeat(N) { idx -> numArr[idx] = nextInt() }
    val sequenceArr = IntArray(N)
    numArr.asSequence().mapIndexed { index, i -> index to i }.sortedBy { it.second }
        .forEachIndexed { index, (originalIndex, _) ->
            sequenceArr[originalIndex] = index
        }
    val topSet = mutableSetOf<Int>()
    val bottomSet = mutableSetOf<Int>()
    var count = 0
    for (idx in sequenceArr) {
        when (idx) {
            in topSet -> {
                topSet.remove(idx)
                topSet.add(idx + 1)
            }
            in bottomSet -> {
                bottomSet.remove(idx)
                bottomSet.add(idx - 1)
            }
            else -> {
                count++
                topSet.add(idx + 1)
                bottomSet.add(idx - 1)
            }
        }
    }
    println(count)
}