package AtCoder.ProblemList.Difficulty800_1199.Paint

fun main() {
    val (H, W, M) = readln().trim().split(" ").map(String::toInt)
    val queries = Array(M) { readln().trim().split(" ").map(String::toInt) }
    var h = H.toLong()
    var w = W.toLong()
    val countArr = LongArray(200001).apply { this[0] = H.toLong() * W }
    val visitedH = BooleanArray(H)
    val visitedW = BooleanArray(W)
    for (i in (M - 1) downTo 0) {
        val (T, A, X) = queries[i]
        if (queries[i][0] == 1) {
            if (visitedH[A - 1]) continue
            visitedH[A - 1] = true
            countArr[X] += w
            countArr[0] -= w
            h--
        } else {
            if (visitedW[A - 1]) continue
            visitedW[A - 1] = true
            countArr[X] += h
            countArr[0] -= h
            w--
        }
    }
    var count = 0
    val sb = StringBuilder()
    for (i in countArr.indices) {
        if (countArr[i] > 0) {
            count++
            sb.appendLine("$i ${countArr[i]}")
        }
    }
    println(count)
    println(sb)
}