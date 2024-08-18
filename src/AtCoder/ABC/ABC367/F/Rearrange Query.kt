package AtCoder.ABC.ABC367.F

import java.util.StringTokenizer

const val MOD = (1L shl 61) - 1
fun main() {
    val (N, Q) = readln().split(" ").map(String::toInt)
    val aArr = ("0 " + readln()).split(" ").map(String::toLong).toLongArray()
    val bArr = ("0 " + readln()).split(" ").map(String::toLong).toLongArray()
    val hashArr = LongArray(N + 1) { (1 until MOD).random() }
    for (i in 1..N) {
        aArr[i] = (aArr[i - 1] + hashArr[aArr[i].toInt()]) % MOD
        bArr[i] = (bArr[i - 1] + hashArr[bArr[i].toInt()]) % MOD
    }
    val sb = StringBuilder()
    repeat(Q) {
        val st = StringTokenizer(readln())
        val l = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        val L = st.nextToken().toInt()
        val R = st.nextToken().toInt()
        if ((MOD + aArr[r] - aArr[l - 1]) % MOD == (MOD + bArr[R] - bArr[L - 1]) % MOD) sb.appendLine("Yes")
        else sb.appendLine("No")
    }
    println(sb)
}