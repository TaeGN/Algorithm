package 백준.Silver.S3.p15654_N과M5

import java.io.StreamTokenizer

@JvmInline
value class Flag(val value: Int = 0) {
    fun addAndNewFlag(idx: Int): Flag = Flag(value or (1 shl idx))
    fun contains(idx: Int): Boolean = (value and (1 shl idx)) != 0
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val numArr = IntArray(n)
    repeat(n) { idx ->
        numArr[idx] = readInt()
    }
    numArr.sort()

    val sb = StringBuilder()
    fun combination(count: Int = 0, flag: Flag = Flag(), selected: IntArray = IntArray(m)) {
        if (count == m) {
            sb.appendLine(selected.joinToString(separator = " "))
            return
        }

        for (i in 0 until n) {
            if (flag.contains(i)) continue
            selected[count] = numArr[i]
            combination(count + 1, flag.addAndNewFlag(i), selected)
        }
    }

    combination()
    println(sb)
}