package 백준.Gold.G2.p11444_피보나치수6

const val MOD = 1_000_000_007
fun main() {
    val n = readln().toLong()
    val fibonacciMap = mutableMapOf(0L to 0, 1L to 1, 2L to 1)
    fun fibonacci(n: Long): Int {
        if (fibonacciMap.containsKey(n)) return fibonacciMap[n]!!
        return if (n % 2 == 0L) ((fibonacci(n / 2).toLong() * (fibonacci(n / 2 + 1) % MOD + fibonacci(n / 2 - 1))) % MOD).toInt()
            .also { fibonacciMap[n] = it }
        else ((fibonacci(n / 2 + 1).toLong() * fibonacci(n / 2 + 1) % MOD + fibonacci(n / 2).toLong() * fibonacci(n / 2) % MOD) % MOD).toInt()
            .also { fibonacciMap[n] = it }
    }

    println(fibonacci(n))
}