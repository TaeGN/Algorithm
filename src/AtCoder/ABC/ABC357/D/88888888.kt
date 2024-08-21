package AtCoder.ABC.ABC357.D

const val MOD = 998244353
fun main() {
    fun Long.pow(n: Long): Long {
        val value = this % MOD
        if (n == 0L) return 1
        if (n == 1L) return value
        val halfPow = value.pow(n / 2)
        return if (n % 2 == 1L) value * halfPow % MOD * halfPow % MOD
        else halfPow * halfPow % MOD
    }

    val N = readln()
    val nLen = N.length
    val a = 10L.pow(nLen.toLong())
    val n = N.toLong()
    println(n % MOD * ((a.pow(n) + MOD - 1) % MOD) % MOD * (a - 1).pow((MOD - 2).toLong()) % MOD)
}