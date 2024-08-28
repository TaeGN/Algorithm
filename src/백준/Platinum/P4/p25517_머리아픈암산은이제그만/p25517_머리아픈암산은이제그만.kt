package 백준.Platinum.P4.p25517_머리아픈암산은이제그만

const val MOD = 1000000007
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val arr = intArrayOf(
        1,
        927880474,
        933245637,
        668123525,
        429277690,
        733333339,
        724464507,
        957939114,
        203191898,
        586445753,
        698611116
    )
    var result = 1L
    for (i in 10 downTo 0) {
        if (N >= i * 100000000) {
            result = arr[i].toLong()
            break
        }
    }
    for (i in (N / 100000000 * 100000000 + 1)..N) {
        result = (result * i) % MOD
    }
    fun Int.pow(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return this
        val halfPow = pow(n / 2)
        return if (n % 2 == 1) ((this.toLong() * halfPow % MOD) * halfPow % MOD).toInt()
        else (halfPow.toLong() * halfPow % MOD).toInt()
    }

    val set = mutableSetOf<Int>()
    repeat(M) {
        val num = readln().toInt()
        if (set.add(num)) result = (result * num.pow(MOD - 2)) % MOD
    }
    println(result)
}