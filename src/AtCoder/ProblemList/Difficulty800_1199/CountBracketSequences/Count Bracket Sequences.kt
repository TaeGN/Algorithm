package AtCoder.ProblemList.Difficulty800_1199.CountBracketSequences

const val MOD = 998244353
fun main() {
    val S = readln().trim()
    val prev = IntArray(1501).apply { this[0] = 1 }
    val next = IntArray(1501)
    for (c in S) {
        when (c) {
            '(' -> {
                for (i in 0 until (prev.size - 1)) {
                    next[i + 1] = prev[i]
                }
            }

            ')' -> {
                for (i in 1 until prev.size) {
                    next[i - 1] = prev[i]
                }
            }

            '?' -> {
                for (i in prev.indices) {
                    if (i > 0) next[i - 1] = (next[i - 1] + prev[i]) % MOD
                    if (i < (prev.size - 1)) next[i + 1] = (next[i + 1] + prev[i]) % MOD
                }
            }
        }
        for (i in prev.indices) {
            prev[i] = next[i]
            next[i] = 0
        }
    }
    println(prev[0])
}