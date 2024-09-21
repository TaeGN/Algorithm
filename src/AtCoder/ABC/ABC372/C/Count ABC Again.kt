package AtCoder.ABC.ABC372.C

fun main() {
    val (N, Q) = readln().split(" ").map(String::toInt)
    val S = readln().toCharArray()
    val hashArr = IntArray(S.size)
    for (i in 0 until S.size - 2) {
        hashArr[i] = (S[i] - 'A') * 676 + (S[i + 1] - 'A') * 26 + (S[i + 2] - 'A')
    }
    val abcHash = 28
    var result = hashArr.count { it == abcHash }
    val sb = StringBuilder()
    repeat(Q) {
        val (X, C) = readln().split(" ").let { (it[0].toInt() - 1) to it[1].first() }
        val diff = C - S[X]
        S[X] = C
        if (hashArr[X] == abcHash) result--
        hashArr[X] += diff * 676
        if (hashArr[X] == abcHash) result++
        if (X >= 1) {
            if (hashArr[X - 1] == abcHash) result--
            hashArr[X - 1] += diff * 26
            if (hashArr[X - 1] == abcHash) result++
        }
        if (X >= 2) {
            if (hashArr[X - 2] == abcHash) result--
            hashArr[X - 2] += diff
            if (hashArr[X - 2] == abcHash) result++
        }
        sb.appendLine(result)
    }
    println(sb)
}