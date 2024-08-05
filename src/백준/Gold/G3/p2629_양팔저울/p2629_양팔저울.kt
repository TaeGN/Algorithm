package 백준.Gold.G3.p2629_양팔저울

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val set = mutableSetOf(0)
    val newSet = mutableSetOf<Int>()
    val n = nextInt()
    repeat(n) {
        val curW = nextInt()
        for (w in set) {
            newSet.add(w + curW)
            newSet.add(w - curW)
        }
        set.addAll(newSet)
        newSet.clear()
    }
    val sb = StringBuilder()
    val m = nextInt()
    repeat(m) {
        sb.append(if (nextInt() in set) "Y" else "N").append(" ")
    }
    println(sb)
}