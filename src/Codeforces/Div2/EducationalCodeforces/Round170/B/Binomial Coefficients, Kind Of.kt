package Codeforces.Div2.EducationalCodeforces.Round170.B

const val MOD = 1_000_000_007
fun main() {
    val sb = StringBuilder()
    val pow2 = IntArray(100001).apply { this[0] = 1 }
    for (i in 1 until pow2.size) {
        pow2[i] = (pow2[i - 1].toLong() * 2 % MOD).toInt()
    }
    val T = readln().toInt()
    val N = readln().trim().split(" ").map(String::toInt)
    val K = readln().trim().split(" ").map(String::toInt)
    for (i in 0 until T) {
        sb.appendLine(if (K[i] == 0 || K[i] == N[i]) 1 else pow2[K[i]])
    }
    println(sb)
}