package 백준.Platinum.P3.p1605_반복부분문자열

const val MOD = 10007
const val POWER = 31
fun main() {
    val L = readln().toInt()
    val S = readln()
    val pow2 = IntArray(L)
    var value = 1
    for (i in 0 until L) {
        pow2[i] = value
        value = (value * POWER) % MOD
    }

    val hashTable = Array(MOD) { mutableListOf<Int>() }
    fun isPossible(len: Int): Boolean {
        hashTable.forEach { it.clear() }
        var hash = 0
        for (i in 0 until len) {
            hash = (hash + (S[i] - 'a') * pow2[len - 1 - i]) % MOD
        }
        hashTable[hash].add(0)
        for (startIdx in 1..(L - len)) {
            hash =
                ((hash + MOD - (S[startIdx - 1] - 'a') * pow2[len - 1] % MOD) * POWER + (S[startIdx + len - 1] - 'a')) % MOD
            if (hashTable[hash].size > 0) {
                for (otherStartIdx in hashTable[hash]) {
                    var isPossible = true
                    for (i in 0 until len) {
                        if (S[startIdx + i] != S[otherStartIdx + i]) {
                            isPossible = false
                            break
                        }
                    }
                    if (isPossible) return true
                }
            }
            hashTable[hash].add(startIdx)
        }
        return false
    }


    fun search(start: Int = 0, end: Int = L): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }

    println(search())
}