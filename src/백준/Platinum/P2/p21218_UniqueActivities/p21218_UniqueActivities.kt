package 백준.Platinum.P2.p21218_UniqueActivities

import kotlin.math.sqrt

val mod = longArrayOf(1_000_000_007, (1L shl 31) - 1)
val power = mod.map { (26 until sqrt(it.toDouble()).toInt()).random() }

data class Hash(val hashArr: LongArray) {
    override fun equals(other: Any?): Boolean {
        if (other is Hash) return hashArr.contentEquals(other.hashArr)
        return super.equals(other)
    }

    override fun hashCode(): Int = hashArr.contentHashCode()
}

fun main() {
    val S = readln()
    val N = S.length
    val powerArr = Array(mod.size) { LongArray(N).apply { this[0] = 1 } }
    for (i in mod.indices) {
        for (j in 1 until N) {
            powerArr[i][j] = powerArr[i][j - 1] * power[i] % mod[i]
        }
    }
    val countArr = IntArray(N)
    fun onlyOneWord(len: Int): String? {
        if (len == 0) return ""
        countArr.fill(0)
        val hash = LongArray(mod.size)
        val map = mutableMapOf<Hash, Int>()
        for (idx in S.indices) {
            for (i in mod.indices) {
                if (idx >= len) hash[i] = (hash[i] + (mod[i] - (S[idx - len] - 'A')
                        * powerArr[i][len - 1] % mod[i])) % mod[i]
                hash[i] = (hash[i] * power[i] + (S[idx] - 'A')) % mod[i]
            }
            if (idx >= len - 1) {
                val nHash = Hash(hash.copyOf())
                if (nHash !in map) map[nHash] = idx
                countArr[map[nHash]!!]++
            }
        }
        val idx = countArr.indexOf(1)
        return if (idx == -1) null else S.substring(idx + 1 - len, idx + 1)
    }

    fun search(start: Int = 1, end: Int = N): String {
        val mid = (start + end) / 2
        if (start == end) return onlyOneWord(start) ?: onlyOneWord(end) ?: ""
        return if (onlyOneWord(mid) != null) search(start, mid)
        else search(mid + 1, end)
    }

    println(search())
}