package 백준.Diamond.D5.p1739_도로정비하기

import java.io.StreamTokenizer

const val EMPTY = -1
const val MAX_N = 1000
const val MAX_M = 1000
const val MAX_NM = MAX_N + MAX_M

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun Int.not() = if (this < MAX_NM) this + MAX_NM else this - MAX_NM
    val outSetList = List(MAX_NM * 2) { mutableSetOf<Int>() }
    val stack = ArrayDeque<Int>()
    val parentArr = IntArray(MAX_NM * 2) { EMPTY }
    val sccIdArr = IntArray(MAX_NM * 2) { EMPTY }
    var sccId = 0
    var id = 0
    var isPossible = true
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        for (to in outSetList[from]) {
            if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
            else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
        }
        if (parent == parentArr[from]) {
            sccId++
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccId
                if (sccIdArr[idx.not()] == sccId) isPossible = false
                if (idx == from) break
            }
        }
        return parent
    }

    fun init() {
        stack.clear()
        sccId = 0
        id = 0
        isPossible = true
        outSetList.forEach { it.clear() }
        parentArr.fill(EMPTY)
        sccIdArr.fill(EMPTY)
    }

    val T = nextInt()
    val sb = StringBuilder()
    repeat(T) {
        val N = nextInt()
        val M = nextInt()
        val K = nextInt()
        init()
        repeat(K) {
            var A = nextInt() - 1
            var B = nextInt() - 1 + MAX_N
            var C = nextInt() - 1
            var D = nextInt() - 1 + MAX_N
            if (B > D) {
                A += MAX_NM
                C += MAX_NM
            }
            if (A > C) {
                B += MAX_NM
                D += MAX_NM
            }
            when {
                A != C && B != D -> {
                    outSetList[B.not()].add(A)
                    outSetList[B.not()].add(D)
                    outSetList[C.not()].add(A)
                    outSetList[C.not()].add(D)
                    outSetList[A.not()].add(B)
                    outSetList[A.not()].add(C)
                    outSetList[D.not()].add(B)
                    outSetList[D.not()].add(C)
                }

                A == C && B != D -> {
                    outSetList[A.not()].add(A)
                }

                A != C && B == D -> {
                    outSetList[B.not()].add(B)
                }
            }
        }
        for(i in 0 until N) {
            if(sccIdArr[i] == EMPTY) scc(i)
            if(sccIdArr[i.not()] == EMPTY) scc(i.not())
            if(!isPossible) break
        }
        for(i in 0 until M) {
            if(sccIdArr[i + MAX_N] == EMPTY) scc(i + MAX_N)
            if(sccIdArr[(i + MAX_N).not()] == EMPTY) scc((i + MAX_N).not())
            if(!isPossible) break
        }
        sb.appendLine(if (isPossible) "Yes" else "No")
    }
    println(sb)
}