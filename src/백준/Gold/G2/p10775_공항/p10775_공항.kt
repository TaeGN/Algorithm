package 백준.Gold.G2.p10775_공항

import java.io.StreamTokenizer
import java.util.TreeSet

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val g = readInt()
    val p = readInt()
    val set = TreeSet((1..g).toList())
    var isOpen = true
    repeat(p) {
        val gi = readInt()
        if (isOpen) {
            val minGateId = set.floor(gi)
            if (minGateId == null) isOpen = false
            else set.remove(minGateId)
        }
    }

    println(g - set.size)
}
