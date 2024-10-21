package AtCoder.ProblemList.Difficulty800_1199.GomamayoSequence

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim()
    val C = readln().trim().split(" ").map(String::toInt)
    val forwardSumC = Array(N) { LongArray(2) }
    val backwardSumC = Array(N) { LongArray(2) }
    for (i in 0 until N) {
        for (j in 0 until 2) {
            forwardSumC[i][j] = forwardSumC.getOrNull(i - 1)?.get(j) ?: 0
            backwardSumC[N - 1 - i][j] = backwardSumC.getOrNull(N - i)?.get(j) ?: 0
        }
        if (S[i].digitToInt() == i % 2) forwardSumC[i][1] += C[i].toLong()
        else forwardSumC[i][0] += C[i].toLong()
        if (S[N - 1 - i].digitToInt() != (N - 1 - i) % 2) backwardSumC[N - 1 - i][1] += C[N - 1 - i].toLong()
        else backwardSumC[N - 1 - i][0] += C[N - 1 - i].toLong()
    }
    var result = Long.MAX_VALUE shr 2
    for (i in 0 until (N - 1)) {
        for (j in 0 until 2) {
            result = minOf(result, forwardSumC[i][j] + backwardSumC[i + 1][j])
        }
    }
    println(result)
}