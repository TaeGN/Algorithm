package 백준.Gold.G3.p9344_도로

import java.io.StreamTokenizer
import java.util.PriorityQueue

const val MAX_N = 10000

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val T = readInt()
    val sb = StringBuilder()
    val pq = PriorityQueue<Triple<Int, Int, Int>>(MAX_N, compareBy { it.third })
    val parents = IntArray(MAX_N + 1)
    fun IntArray.init(n: Int) {
        for (i in 1..n) {
            this[i] = i
        }
    }
    fun IntArray.find(id: Int): Int = if (this[id] == id) id else find(this[id]).also { this[id] = id }
    fun IntArray.union(id1: Int, id2: Int): Boolean = (find(id1) != find(id2)).also { this[find(id2)] = find(id1) }
    fun PriorityQueue<Triple<Int, Int, Int>>.isPossible(p: Int, q: Int): Boolean = (0 until pq.size).any {
        poll().let { parents.union(it.first, it.second) && p == it.first && q == it.second }
    }.also { clear() }

    repeat(T) {
        val N = readInt()
        val M = readInt()
        val p = readInt()
        val q = readInt()
        parents.init(N)
        repeat(M) {
            pq.add(Triple(readInt(), readInt(), readInt()))
        }

        if (pq.isPossible(p, q)) sb.appendLine("YES")
        else sb.appendLine("NO")
    }

    println(sb)
}