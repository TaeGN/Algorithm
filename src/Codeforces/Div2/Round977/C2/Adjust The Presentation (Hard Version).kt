package Codeforces.Div2.Round977.C2

import java.util.TreeSet

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M, Q) = readln().trim().split(" ").map(String::toInt)
        val A = readln().trim().split(" ").map(String::toInt)
        val B = readln().trim().split(" ").map(String::toInt).toIntArray()
        val map = A.withIndex().associate { it.value to it.index }
        val setList = List(N) { TreeSet<Int>() }
        B.forEachIndexed { index, b -> setList[map[b]!!].add(index) }
        val arr = IntArray(N + Q * 2)
        var arrSize = 0
        var arrIdx = 0
        fun isPossible(idx: Int): Boolean {
            if (idx > 0) {
                if (setList[idx].isNotEmpty() && setList[idx - 1].isEmpty()) return false
                if (setList[idx].isNotEmpty() && setList[idx - 1].first() > setList[idx].first()) return false
            }
            if (idx < (N - 1)) {
                if (setList[idx + 1].isNotEmpty() && setList[idx].isEmpty()) return false
                if (setList[idx + 1].isNotEmpty() && setList[idx].first() > setList[idx + 1].first()) return false
            }
            return true
        }
        for (idx in 0 until N) {
            if (!isPossible(idx)) arr[arrSize++] = idx
        }
        fun result(): String {
            while (arrIdx < arrSize) {
                val idx = arr[arrIdx]
                if (!isPossible(idx)) return "TIDAK"
                arrIdx++
            }
            return "YA"
        }
        sb.appendLine(result())
        repeat(Q) {
            val (S, T) = readln().trim().split(" ").map(String::toInt)
            val pB = B[S - 1]
            val pIdx = map[pB]!!
            B[S - 1] = T
            val nIdx = map[T]!!
            setList[pIdx].remove(S - 1)
            setList[nIdx].add(S - 1)
            if (!isPossible(pIdx)) arr[arrSize++] = pIdx
            if (!isPossible(nIdx)) arr[arrSize++] = nIdx
            sb.appendLine(result())
        }
    }
    println(sb)
}