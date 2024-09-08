package 백준.Platinum.P2.p17228_아름다운만영로

const val MOD = 1_000_000_009
const val POWER = 31
fun main() {
    val N = readln().toInt()
    val powerOf = IntArray(N + 1).apply { this[1] = 1 }
    for (i in 2..N) {
        powerOf[i] = ((powerOf[i - 1].toLong() * POWER) % MOD).toInt()
    }
    val outLists = List(N + 1) { mutableListOf<Pair<Int, Char>>() }
    repeat(N - 1) { readln().trim().split(" ").let { outLists[it[0].toInt()].add(it[1].toInt() to it[2].first()) } }
    val P = readln()
    fun result(): Int {
        if (P.length >= N) return 0
        val targetHash =
            P.foldIndexed(0) { idx, acc, c -> ((acc + (c - 'a').toLong() * powerOf[P.length - idx]) % MOD).toInt() }
        var result = 0
        fun dfs(from: Int = 1, hash: Int = 0, idx: Int = 0, wordArr: CharArray = CharArray(N)) {
            if (idx >= P.length && hash == targetHash) result++
            for ((to, c) in outLists[from]) {
                val nHash = if (idx < P.length) ((hash + (c - 'a').toLong() * powerOf[P.length - idx]) % MOD).toInt()
                else (((hash + MOD - (wordArr[idx - P.length] - 'a').toLong() * powerOf[P.length] % MOD) * POWER + (c - 'a')) % MOD).toInt()
                wordArr[idx] = c
                dfs(to, nHash, idx + 1, wordArr)
            }
        }
        dfs()
        return result
    }
    println(result())
}