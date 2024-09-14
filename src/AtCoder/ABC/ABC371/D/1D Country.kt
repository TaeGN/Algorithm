package AtCoder.ABC.ABC371.D

fun main() {
    val N = readln().toInt()
    val X = readln().split(" ").map(String::toInt)
    val P = readln().split(" ").map(String::toInt)
    val sumP = LongArray(N)
    for (i in 0 until N) {
        sumP[i] = sumP.getOrElse(i - 1) { 0L } + P[i]
    }
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (L, R) = readln().split(" ").map(String::toInt)
        val l = X.binarySearch(L).let { if (it >= 0) it else -it - 1 }
        val r = X.binarySearch(R + 1).let { if (it >= 0) it else -it - 1 } - 1
        sb.appendLine(if (l > r) 0 else sumP[r] - sumP.getOrElse(l - 1) { 0 })
    }
    println(sb)
}