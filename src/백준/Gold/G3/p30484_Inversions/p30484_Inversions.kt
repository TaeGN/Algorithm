package 백준.Gold.G3.p30484_Inversions

const val MOD = 1_000_000_007

fun main() {
    val word = readln()
    val N = readln().toLong()
    val totalAlphabetCountArr = IntArray(26)
    val alphabetCountByIdxToEnd = List(word.length) { IntArray(26) }
    for (i in word.length - 1 downTo 0) {
        totalAlphabetCountArr[word[i] - 'a']++
        for (j in 0 until 26) {
            alphabetCountByIdxToEnd[i][j] = alphabetCountByIdxToEnd.getOrNull(i + 1)?.get(j) ?: 0
        }
        for (j in word[i] - 'a' until 26) {
            alphabetCountByIdxToEnd[i][j]++
        }
    }
    var result = 0L
    for (i in 1 until 26) {
        totalAlphabetCountArr[i] += totalAlphabetCountArr[i - 1]
    }
    for ((idx, c) in word.withIndex()) {
        result += (alphabetCountByIdxToEnd.getOrNull(idx + 1)?.getOrElse(c - 'a' - 1) { 0 } ?: 0).toLong() *
                (N % MOD) % MOD
        result += totalAlphabetCountArr.getOrElse(c - 'a' - 1) { 0 }.toLong() *
                ((if (N % 2 == 0L) (N / 2 % MOD) * ((N - 1) % MOD) else ((N - 1) / 2 % MOD) * (N % MOD)) % MOD) % MOD
        result %= MOD
    }
    println(result)
}