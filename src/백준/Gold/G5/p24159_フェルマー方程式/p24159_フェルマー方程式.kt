package 백준.Gold.G5.p24159_フェルマー方程式

fun main() {
    fun Int.pow(n: Int, mod: Int): Int {
        if (this >= mod) return (this % mod).pow(n, mod)
        if (n == 0) return 1
        if (n == 1) return this
        val halfPow = pow(n / 2, mod)
        return if (n % 2 == 1) this * halfPow % mod * halfPow % mod
        else halfPow * halfPow % mod
    }

    val P = readln().toInt()
    val N = readln().toInt()
    val pArr = IntArray(P)
    for (i in 0 until P) {
        pArr[i.pow(N, P)]++
    }
    var result = 0
    for (i in 0 until P) {
        if (pArr[i] == 0) continue
        for (j in 0 until P) {
            val k = (i + j) % P
            if (pArr[j] == 0 || pArr[k] == 0) continue
            result += pArr[i] * pArr[j] * pArr[k]
        }
    }
    println(result)
}