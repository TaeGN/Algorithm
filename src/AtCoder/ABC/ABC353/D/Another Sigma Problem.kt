package AtCoder.ABC.ABC353.D

const val MOD = 998244353
fun main() {
    val N = readln().toInt()
    val aArr = readln().trim().split(" ").map(String::toInt)
    val aLenArr = aArr.map { it.toString().length }
    val pow10 = LongArray(11).apply { this[0] = 1 }
    for (i in 1..10) {
        pow10[i] = pow10[i - 1] * 10
    }
    var sum = 0L
    var result = 0L
    for (i in (N - 1) downTo 0) {
        result = (result + (aArr[i].toLong() * i) % MOD) % MOD
        result = (result + (aArr[i].toLong() * sum) % MOD) % MOD
        sum = (sum + pow10[aLenArr[i]]) % MOD
    }
    println(result)
}