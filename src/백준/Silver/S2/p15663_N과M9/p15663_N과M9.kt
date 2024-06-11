package 백준.Silver.S2.p15663_N과M9

import java.io.StreamTokenizer

@JvmInline
value class Flag(private val flag: Int = 0) {
    fun contains(idx: Int): Boolean = (flag and (1 shl idx)) != 0
    fun addAndNewInstance(idx: Int): Flag = Flag(flag or (1 shl idx))
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
    val selectedSet = mutableSetOf<String>()
    fun combination(count: Int = 0, flag: Flag = Flag(), selected: IntArray = IntArray(m)) {
        if (count == m) {
            val curSelected = selected.joinToString(separator = " ")
            if (selectedSet.add(curSelected)) {
                sb.appendLine(curSelected)
            }
            return
        }

        for (i in 0 until n) {
            if (flag.contains(i)) continue
            selected[count] = numArr[i]
            combination(count + 1, flag.addAndNewInstance(i), selected)
        }
    }

    combination()
    println(sb)
}