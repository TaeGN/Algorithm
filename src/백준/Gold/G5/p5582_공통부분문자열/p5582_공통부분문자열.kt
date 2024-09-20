package 백준.Gold.G5.p5582_공통부분문자열

import kotlin.math.sqrt

val mod = longArrayOf((1L shl 31) - 1, 1_000_000_007)
val power = IntArray(mod.size) { (26 until sqrt(mod[it].toDouble()).toInt()).random() }

data class Hash(val hashArr: LongArray) {
    override fun equals(other: Any?): Boolean {
        if (other is Hash) return hashArr.contentEquals(other.hashArr)
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return hashArr.contentHashCode()
    }
}

fun main() {
    val S1 = readln()
    val S2 = readln()
    val maxLen = minOf(S1.length, S2.length)
    val powerArr = Array(mod.size) { LongArray(maxLen).apply { this[0] = 1 } }
    for (i in mod.indices) {
        for (j in 1 until maxLen) {
            powerArr[i][j] = powerArr[i][j - 1] * power[i] % mod[i]
        }
    }
    fun isPossible(len: Int): Boolean {
        if (len == 0) return true
        val set = mutableSetOf<Hash>()
        val hash = LongArray(mod.size)
        for (j in S1.indices) {
            for (i in mod.indices) {
                if (j >= len) {
                    hash[i] = (hash[i] + mod[i] - (S1[j - len] - 'A') * powerArr[i][len - 1] % mod[i]) % mod[i]
                }
                hash[i] = (hash[i] * power[i] + (S1[j] - 'A')) % mod[i]
            }
            if (j >= len - 1) set.add(Hash(hash.copyOf()))
        }
        hash.fill(0)
        for (j in S2.indices) {
            for (i in mod.indices) {
                if (j >= len) {
                    hash[i] = (hash[i] + mod[i] - (S2[j - len] - 'A') * powerArr[i][len - 1] % mod[i]) % mod[i]
                }
                hash[i] = (hash[i] * power[i] + (S2[j] - 'A')) % mod[i]
            }
            if (j >= len - 1 && Hash(hash.copyOf()) in set) return true
        }
        return false
    }

    fun search(start: Int = 0, end: Int = maxLen): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }
    println(search())
}