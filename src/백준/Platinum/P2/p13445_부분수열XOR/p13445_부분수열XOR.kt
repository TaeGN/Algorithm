package 백준.Platinum.P2.p13445_부분수열XOR

class Trie(val idx: Int) {
    val children = Array<Trie?>(2) { null }
    var count = 0
    fun add(num: Int) {
        count++
        if (idx == 17) return
        getChild(getBit(num)).add(num)
    }

    private fun getChild(bit: Int): Trie {
        if (children[bit] == null) children[bit] = Trie(idx + 1)
        return children[bit]!!
    }

    private fun getBit(num: Int) = if ((num and (1 shl (16 - idx))) != 0) 1 else 0

    fun countLowerK(num: Int, k: Int, sum: Int = 0): Int {
        if (sum >= k) return 0
        if (sum + (1 shl (17 - idx)) <= k) return count
        var result = 0
        val bit = getBit(num)
        if (children[bit] != null) {
            result += children[bit]!!.countLowerK(num, k, sum)
        }
        if (children[(bit + 1) % 2] != null) {
            result += children[(bit + 1) % 2]!!.countLowerK(num, k, sum + (1 shl (16 - idx)))
        }
        return result
    }
}

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val aArr = readln().trim().split(" ").map(String::toInt)
    val trie = Trie(0)
    var result = 0L
    var subsetXor = 0
    trie.add(0)
    for (a in aArr) {
        subsetXor = subsetXor xor a
        result += trie.countLowerK(subsetXor, K)
        trie.add(subsetXor)
    }
    println(result)
}