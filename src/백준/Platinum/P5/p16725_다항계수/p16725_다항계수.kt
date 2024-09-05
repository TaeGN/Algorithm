package 백준.Platinum.P5.p16725_다항계수

const val MOD = 1_000_000_009
fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    fun result(): Long {
        val arr = LongArray(K + 1)
        arr.fill(1, 0, minOf(K + 1, N + 1))
        val sumArr = LongArray(K + 1)
        for (k in 1 until M) {
            for (i in 0..minOf(K, N * M)) {
                sumArr[i] = (sumArr[i] + arr[i]) % MOD
                if (i + N + 1 <= K) sumArr[i + N + 1] = (sumArr[i + N + 1] - arr[i] + MOD) % MOD
            }
            var value = 0L
            for (i in 0..minOf(K, N * (M + 1))) {
                value = (value + sumArr[i]) % MOD
                arr[i] = value
            }
            sumArr.fill(0, 0, minOf(K, N * (M + 1)) + 1)
        }
        return arr[K]
    }
    println(result())
}