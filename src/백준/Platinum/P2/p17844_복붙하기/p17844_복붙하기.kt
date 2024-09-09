package 백준.Platinum.P2.p17844_복붙하기

import kotlin.math.sqrt

val mod = longArrayOf((1L shl 31) - 1, 1_000_000_007, 1_000_000_009, 998244353)
val power = mod.map { (26 until sqrt(it.toDouble()).toInt()).random() }

class Hash(val arr: LongArray) {
    override fun equals(other: Any?): Boolean {
        if (other is Hash) return arr.contentEquals(other.arr)
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return arr.contentHashCode()
    }
}

fun main() {
    val S = readln()
    val primeArr = Array(mod.size) { LongArray(S.length).apply { this[0] = 1 } }
    for (i in mod.indices) {
        for (j in 1 until S.length) {
            primeArr[i][j] = primeArr[i][j - 1] * power[i] % mod[i]
        }
    }

    fun isPossible(len: Int): Boolean {
        if (len == 0) return true
        val map = mutableMapOf<Hash, Int>()
        val hash = LongArray(mod.size)
        for (i in S.indices) {
            if (i >= len) {
                for (j in mod.indices) {
                    hash[j] = (hash[j] + (mod[j] - (S[i - len] - 'a') * primeArr[j][len - 1] % mod[j])) % mod[j]
                }
            }
            for (j in mod.indices) {
                hash[j] = (hash[j] * power[j] + (S[i] - 'a')) % mod[j]
            }
            val nHash = Hash(hash.copyOf())
            if (i >= len - 1) {
                if (i - map.getOrDefault(nHash, S.length) >= len) return true
                if (nHash !in map) map[nHash] = i
            }
        }
        return false
    }

    fun search(start: Int = 0, end: Int = S.length): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }
    println(search().let { if (it == 0) -1 else it })
}