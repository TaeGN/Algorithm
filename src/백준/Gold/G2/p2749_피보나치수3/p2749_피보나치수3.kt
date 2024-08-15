package 백준.Gold.G2.p2749_피보나치수3

const val MOD = 1_000_000

operator fun Array<IntArray>.times(o: Array<IntArray>) = arrayOf(
    intArrayOf(
        ((this[0][0].toLong() * o[0][0] % MOD + this[0][1].toLong() * o[1][0] % MOD) % MOD).toInt(),
        ((this[0][0].toLong() * o[0][1] % MOD + this[0][1].toLong() * o[1][1] % MOD) % MOD).toInt()
    ),
    intArrayOf(
        ((this[1][0].toLong() * o[0][0] % MOD + this[1][1].toLong() * o[1][0] % MOD) % MOD).toInt(),
        ((this[1][0].toLong() * o[0][1] % MOD + this[1][1].toLong() * o[1][1] % MOD) % MOD).toInt()
    )
)

fun main() {
    val n = readln().toLong()
    val arr = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
    fun Array<IntArray>.pow(n: Long): Array<IntArray> {
        if (n == 1L) return this
        val halfPow = pow(n / 2)
        return if (n % 2 == 1L) this * halfPow * halfPow
        else halfPow * halfPow
    }
    println(arr.pow(n)[0][1])
}