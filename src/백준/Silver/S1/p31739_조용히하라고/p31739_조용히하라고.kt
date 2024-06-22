package 백준.Silver.S1.p31739_조용히하라고

import java.io.StreamTokenizer
import kotlin.math.abs
import kotlin.math.max

data class Mosquito(val r: Int, val c: Int, val hp: Int)

val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = readInt()
    val M = readInt()
    val K = readInt()
    val T = readInt()
    val P = readInt()

    val mosquitoList = mutableListOf<Mosquito>()
    repeat(K) {
        val r = readInt()
        val c = readInt()
        val hp = readInt()
        mosquitoList.add(Mosquito(r, c, hp))
    }

    fun Mosquito.distance(other: Mosquito): Int = abs(r - other.r) + abs(c - other.c)
    fun wooMaxCount(count: Int = 0, selected: IntArray = IntArray(K)): Int {
        if (count == selected.size) {
            var distance = 0
            for(idx in 1 until count) {
                distance += mosquitoList[selected[idx]].distance(mosquitoList[selected[idx - 1]])
                if(distance > T) return idx
            }
            return count
        }

        var wooMaxCount = 0
        for (i in mosquitoList.indices) {
            if (selected.asSequence().take(count).any { it == i }) continue
            selected[count] = i
            wooMaxCount = max(wooMaxCount, wooMaxCount(count + 1, selected))
        }
        return wooMaxCount
    }

    fun ahMaxCount(r: Int, c: Int): Int =
        mosquitoList.count { (it.r == r && it.c == c) || it.hp <= P / (abs(it.r - r).toDouble() + abs(it.c - c)) }

    val wooMaxCount = wooMaxCount()
    var ahMaxCount = 0
    for (r in 1..N) {
        for (c in 1..M) {
            ahMaxCount = max(ahMaxCount, ahMaxCount(r, c))
        }
    }

    println("$wooMaxCount $ahMaxCount")
}