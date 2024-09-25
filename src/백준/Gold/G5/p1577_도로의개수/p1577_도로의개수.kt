package 백준.Gold.G5.p1577_도로의개수

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    fun hash(r: Int, c: Int) = r * (M + 1) + c
    val inLists = List((N + 1) * (M + 1)) { mutableSetOf<Int>() }
    for (r in 0..N) {
        for (c in 0..M) {
            if (r > 0) inLists[hash(r, c)].add(hash(r - 1, c))
            if (c > 0) inLists[hash(r, c)].add(hash(r, c - 1))
        }
    }
    repeat(readln().toInt()) {
        readln().trim().split(" ").map(String::toInt).let {
            val hash1 = hash(it[0], it[1])
            val hash2 = hash(it[2], it[3])
            inLists[hash1].remove(hash2)
            inLists[hash2].remove(hash1)
        }
    }

    val dp = LongArray((N + 1) * (M + 1)).apply { this[0] = 1 }
    for (hash in inLists.indices) {
        for (pHash in inLists[hash]) {
            dp[hash] += dp[pHash]
        }
    }
    println(dp.last())
}