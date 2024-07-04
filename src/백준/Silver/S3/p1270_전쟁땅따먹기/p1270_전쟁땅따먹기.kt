package 백준.Silver.S3.p1270_전쟁땅따먹기

import java.io.StreamTokenizer

const val MAX_TI = 100_000
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readLong(): Long {
        nextToken()
        return nval.toLong()
    }

    fun readInt(): Int = readLong().toInt()
    val n = readInt()
    val sb = StringBuilder()
    val TiArr = LongArray(MAX_TI)
    repeat(n) {
        val Ti = readInt()
        repeat(Ti) { idx ->
            TiArr[idx] = readLong()
        }
        val (id, count) = TiArr.asSequence().take(Ti).groupingBy { it }.eachCount().maxBy { it.value }
        sb.appendLine(if (count > Ti / 2) id else "SYJKGW")
    }
    println(sb)
}