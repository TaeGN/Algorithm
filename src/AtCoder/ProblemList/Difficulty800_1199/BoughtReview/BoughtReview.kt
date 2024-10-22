package AtCoder.ProblemList.Difficulty800_1199.BoughtReview

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val A = readln().trim().split(" ").map(String::toInt)
        val P = readln().trim().split(" ").map(String::toInt)
        val minCount = A.foldIndexed(0L) { index, acc, i -> acc + (2 - index).toLong() * i }
        var result = minOf(P[3] * minCount, P[4] * ((minCount - 1) / 2 + 1))
        if (minCount > 1) result = minOf(result, P[3] + P[4] * (minCount / 2))
        sb.appendLine(maxOf(0, result))
    }
    println(sb)
}