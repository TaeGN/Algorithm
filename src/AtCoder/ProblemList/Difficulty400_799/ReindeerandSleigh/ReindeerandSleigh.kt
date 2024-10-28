package AtCoder.ProblemList.Difficulty400_799.ReindeerandSleigh

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val R = readln().trim().split(" ").map(String::toInt).sorted()
    val rSum = LongArray(N)
    for (i in 0 until N) {
        rSum[i] = rSum.getOrElse(i - 1) { 0 } + R[i]
    }
    val sb = StringBuilder()
    repeat(Q) {
        val X = readln().toLong()
        val count = rSum.binarySearch(X + 1).let { if (it >= 0) it else -it - 1 }
        sb.appendLine(count)
    }
    println(sb)
}