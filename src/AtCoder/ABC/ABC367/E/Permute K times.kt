package AtCoder.ABC.ABC367.E

import java.util.StringTokenizer
import kotlin.math.ceil
import kotlin.math.log2

const val MAX_K = 1_000_000_000_000_000_000
fun main() {
    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toLong()
    val xArr = ("0 " + readln()).split(" ").map(String::toInt).toIntArray()
    val aArr = ("0 " + readln()).split(" ").map(String::toInt).toIntArray()
    val doubling = Array(ceil(log2(MAX_K.toDouble())).toInt()) { IntArray(N + 1) }.apply { this[0] = xArr }
    for (i in 1 until doubling.size) {
        for (j in 1..N) {
            doubling[i][j] = doubling[i - 1][doubling[i - 1][j]]
        }
    }
    val sb = StringBuilder()
    for (i in 1..N) {
        var idx = i
        for (j in doubling.indices) {
            if ((K and (1L shl j)) != 0L) idx = doubling[j][idx]
        }
        sb.append("${aArr[idx]} ")
    }
    println(sb)
}