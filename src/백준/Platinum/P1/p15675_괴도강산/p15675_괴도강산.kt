package 백준.Platinum.P1.p15675_괴도강산

const val EMPTY = -1

class Stack(initialCapacity: Int) {
    private val stack = IntArray(initialCapacity)
    private var size = 0
    fun push(value: Int) {
        stack[size++] = value
    }

    fun pop() = stack[--size]
}

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val NM = N + M
    val outLists = Array(2 * NM) { mutableListOf<Int>() }
    fun Int.not() = if (this < NM) this + NM else this - NM
    for (r in 0 until N) {
        val input = readln()
        for (c in 0 until M) {
            val rr = r + M
            val cc = c
            when (input[c]) {
                '*' -> {
                    outLists[rr.not()].add(cc)
                    outLists[cc.not()].add(rr)
                    outLists[rr].add(cc.not())
                    outLists[cc].add(rr.not())
                }

                '#' -> {
                    outLists[rr].add(cc)
                    outLists[cc].add(rr)
                    outLists[rr.not()].add(cc.not())
                    outLists[cc.not()].add(rr.not())
                }
            }
        }
    }

    val stack = Stack(2 * NM)
    val parentArr = IntArray(2 * NM) { EMPTY }
    val sccIdArr = IntArray(2 * NM) { EMPTY }
    var sccId = 0
    var id = 0
    var isPossible = true
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.push(from)
        var parent = parentArr[from]
        for (to in outLists[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
            else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
            if (!isPossible) return EMPTY
        }
        if (parent == parentArr[from]) {
            sccId++
            while (true) {
                val idx = stack.pop()
                sccIdArr[idx] = sccId
                if (sccIdArr[idx.not()] == sccId) isPossible = false
                if (idx == from) break
            }
        }
        return parent
    }
    for (i in 0 until NM) {
        if (sccIdArr[i] == EMPTY && outLists[i].isNotEmpty()) scc(i)
        if (!isPossible) break
    }
    println(if (isPossible) 1 else 0)
}