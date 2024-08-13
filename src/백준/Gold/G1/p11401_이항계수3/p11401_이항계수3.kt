package 백준.Gold.G1.p11401_이항계수3

import java.util.StringTokenizer

const val MOD = 1_000_000_007
fun main() {
    fun Int.pow(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return this
        return if (n % 2 == 1) (this.toLong() * pow(n / 2).let { (it.toLong() * it % MOD).toInt() } % MOD).toInt()
        else pow(n / 2).let { (it.toLong() * it % MOD).toInt() }
    }

    val st = StringTokenizer(readln())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    var kf = 1
    var nkf = 1
    var nf = 1
    for (i in 1..N) {
        nf = (nf.toLong() * i % MOD).toInt()
        if (i == K) kf = nf
        if (i == N - K) nkf = nf
    }

    println((nf.toLong() * nkf.pow(MOD - 2) % MOD) * kf.pow(MOD - 2) % MOD)
}