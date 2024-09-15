package Codeforces.Div2.Round972.C

const val IMPOSSIBLE = Long.MIN_VALUE shr 2
fun main() {
    val sb = StringBuilder()
    val arr = charArrayOf('n', 'a', 'r', 'e', 'k')
    val set = arr.toSet()
    repeat(readln().toInt()) {
        val (N, M) = readln().split(" ").map(String::toInt)
        val scoreArr = LongArray(arr.size) { IMPOSSIBLE }.apply { this[0] = 0 }
        val nextScoreArr = LongArray(arr.size) { IMPOSSIBLE }
        repeat(N) {
            val S = readln()
            for (idx in arr.indices) {
                if (scoreArr[idx] == IMPOSSIBLE) continue
                var arrIdx = idx
                var cScore = 0L
                for (c in S) {
                    if (c == arr[arrIdx % arr.size]) arrIdx++
                    else if (c in set) cScore++
                }
                val nScore = arrIdx / arr.size * arr.size
                val curValue = nScore - cScore
                nextScoreArr[arrIdx % arr.size] = maxOf(nextScoreArr[arrIdx % arr.size], scoreArr[idx] + curValue)
            }
            for (i in arr.indices) {
                scoreArr[i] = maxOf(scoreArr[i], nextScoreArr[i])
            }
            nextScoreArr.fill(IMPOSSIBLE)
        }
        scoreArr.forEachIndexed { index, _ -> scoreArr[index] -= index.toLong() }
        sb.appendLine(scoreArr.max())
    }
    println(sb)
}