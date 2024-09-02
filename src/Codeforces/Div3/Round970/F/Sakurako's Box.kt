package Codeforces.Div3.Round970.F

const val MOD = 1_000_000_007
fun main() {
    fun Int.pow(n: Int): Int {
        if (n == 0) return 1
        if (n == 1) return this
        val halfPow = pow(n / 2)
        return if (n % 2 == 1) (this.toLong() * halfPow % MOD * halfPow % MOD).toInt()
        else (halfPow.toLong() * halfPow % MOD).toInt()
    }

    val sb = StringBuilder()
    val sumArr = LongArray(200001)
    val numArr = IntArray(200001)
    repeat(readln().toInt()) {
        val N = readln().toInt()
        readln().trim().split(" ").map(String::toInt)
            .forEachIndexed { i, num ->
                numArr[i] = num
                sumArr[i] = (sumArr.getOrElse(i - 1) { 0 } + num) % MOD
            }
        var result = 0L
        for (i in (N - 1) downTo 0) {
            result = (result + sumArr.getOrElse(i - 1) { 0 } * numArr[i] % MOD) % MOD
        }
        result = result * (N.toLong() * (N - 1) / 2 % MOD).toInt().pow(MOD - 2) % MOD
        sb.appendLine(result)
    }
    println(sb)
}