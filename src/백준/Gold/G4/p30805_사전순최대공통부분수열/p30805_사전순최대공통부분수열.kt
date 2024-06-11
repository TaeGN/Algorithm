package 백준.Gold.G4.p30805_사전순최대공통부분수열

import java.io.StreamTokenizer
import java.util.PriorityQueue

data class Number(val value: Int, val idx: Int)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val comparator = compareBy<Number>({ -it.value }, { it.idx })
    val n = readInt()
    val pqA = PriorityQueue(n, comparator)
    repeat(n) { idx ->
        pqA.add(Number(readInt(), idx))
    }

    val m = readInt()
    val pqB = PriorityQueue(m, comparator)
    repeat(m) { idx ->
        pqB.add(Number(readInt(), idx))
    }

    var lastIdxA = -1
    var lastIdxB = -1
    val numList = mutableListOf<Int>()
    while (pqA.isNotEmpty() && pqB.isNotEmpty()) {
        val numA = pqA.peek()
        val numB = pqB.peek()
        when {
            numA.idx < lastIdxA -> pqA.poll()
            numB.idx < lastIdxB -> pqB.poll()
            numA.value > numB.value -> pqA.poll()
            numA.value < numB.value -> pqB.poll()
            else -> {
                numList.add(numA.value)
                lastIdxA = pqA.poll().idx
                lastIdxB = pqB.poll().idx
            }
        }
    }

    println(numList.size)
    println(numList.joinToString(separator = " "))
}