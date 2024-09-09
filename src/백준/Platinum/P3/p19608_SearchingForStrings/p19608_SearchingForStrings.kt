package 백준.Platinum.P3.p19608_SearchingForStrings

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
    val N = readln()
    val H = readln()
    val primePowN = LongArray(mod.size) { 1 }
    for (i in 1 until N.length) {
        for (j in mod.indices) {
            primePowN[j] = primePowN[j] * power[j] % mod[j]
        }
    }
    val targetCountArr = IntArray(26)
    N.forEach { targetCountArr[it - 'a']++ }

    fun result(): Int {
        if (N.length > H.length) return 0
        val selected = mutableSetOf<Hash>()
        val countArr = IntArray(26)
        val hash = LongArray(mod.size)
        for (i in H.indices) {
            if (i >= N.length) {
                countArr[H[i - N.length] - 'a']--
                for (j in mod.indices) {
                    hash[j] = (hash[j] + (mod[j] - (H[i - N.length] - 'a') * primePowN[j] % mod[j])) % mod[j]
                }
            }
            countArr[H[i] - 'a']++
            for (j in mod.indices) {
                hash[j] = (hash[j] * power[j] + (H[i] - 'a')) % mod[j]
            }
            if (i >= N.length - 1 && targetCountArr.contentEquals(countArr)) selected.add(Hash(hash.copyOf()))
        }
        return selected.size
    }
    println(result())
}