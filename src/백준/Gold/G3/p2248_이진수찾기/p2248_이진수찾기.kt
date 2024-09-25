package 백준.Gold.G3.p2248_이진수찾기

const val MOD = ((1L shl 31) - 1).toInt()

fun main() {
    val fac = LongArray(31).apply { this[0] = 1 }
    for (i in 1 until fac.size) {
        fac[i] = fac[i - 1] * i % MOD
    }
    infix fun Int.pow(n: Int): Long {
        if (n == 0) return 1
        if (n == 1) return this.toLong()
        val halfPow = pow(n / 2)
        return if (n % 2 == 1) this.toLong() * halfPow % MOD * halfPow % MOD
        else halfPow * halfPow % MOD
    }

    infix fun Int.comb(k: Int) =
        fac[this] * (fac[this - k].toInt() pow (MOD - 2)) % MOD * (fac[k].toInt() pow (MOD - 2)) % MOD

    infix fun Int.sumComb(k: Int): Long {
        var result = 0L
        for (i in 0..k) {
            result += comb(i)
        }
        return result
    }
    val (N, L, I) = readln().trim().split(" ").let { Triple(it[0].toInt(), it[1].toInt(), it[2].toLong()) }
    val sb = StringBuilder()
    var count = 0L
    var oneCount = 0
    for (i in (N - 1) downTo 0) {
        val curCount = i sumComb minOf(i, L - oneCount)
        if (curCount >= (I - count)) sb.append(0)
        else {
            count += curCount
            oneCount++
            sb.append(1)
        }
        if (oneCount == L) {
            sb.append("0".repeat(i))
            break
        }
    }
    println(sb)
}