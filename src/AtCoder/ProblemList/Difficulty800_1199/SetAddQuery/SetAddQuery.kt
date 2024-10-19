package AtCoder.ProblemList.Difficulty800_1199.SetAddQuery

const val EMPTY = -1
fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val X = readln().trim().split(" ").map(String::toInt)
    val S = LongArray(Q)
    val prevIdxArr = IntArray(N) { EMPTY }
    var count = 0
    val result = LongArray(N)
    for (i in 0 until Q) {
        val idx = X[i] - 1
        val prevIdx = prevIdxArr[idx]
        if (prevIdx == EMPTY) {
            count++
            prevIdxArr[idx] = i
        } else {
            count--
            result[idx] += S[i - 1] - S.getOrElse(prevIdx - 1) { 0 }
            prevIdxArr[idx] = EMPTY
        }
        S[i] += S.getOrElse(i - 1) { 0 } + count
    }
    for (i in 0 until N) {
        if (prevIdxArr[i] != -1) result[i] += S[Q - 1] - S.getOrElse(prevIdxArr[i] - 1) { 0 }
    }
    println(result.joinToString(" "))
}