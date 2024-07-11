package 백준.Silver.S2.p30619_내집마련하기

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val N = nextInt()
    val idArr = IntArray(N)
    repeat(N) { idx ->
        idArr[idx] = nextInt()
    }

    val M = nextInt()
    val copyIdArr = idArr.copyOf()
    val sb = StringBuilder()
    repeat(M) {
        val L = nextInt()
        val R = nextInt()
        var curNum = L
        for (id in copyIdArr) {
            if (id in L..R) sb.append("${curNum++} ")
            else sb.append("$id ")
        }
        sb.appendLine()
    }

    println(sb)
}