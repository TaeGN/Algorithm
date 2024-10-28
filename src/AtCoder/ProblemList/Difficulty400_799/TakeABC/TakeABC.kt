package AtCoder.ProblemList.Difficulty400_799.TakeABC

fun main() {
    val S = readln().trim()
    val pIdxArr = IntArray(S.length + 1) { it - 1 }
    for (idx in S.indices) {
        if (pIdxArr[idx] >= 0 && pIdxArr[pIdxArr[idx]] >= 0 && S[idx] == 'C' && S[pIdxArr[idx]] == 'B' && S[pIdxArr[pIdxArr[idx]]] == 'A') {
            pIdxArr[idx + 1] = pIdxArr[pIdxArr[pIdxArr[idx]]]
        }
    }
    val sb = StringBuilder()
    var idx = pIdxArr.last()
    while (idx >= 0) {
        sb.append(S[idx])
        idx = pIdxArr[idx]
    }
    println(sb.reverse())
}