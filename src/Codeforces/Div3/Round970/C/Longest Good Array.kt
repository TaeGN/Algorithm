package Codeforces.Div3.Round970.C

fun main() {
    val sb = StringBuilder()
    val sumArr = IntArray(65535) { it }
    for (i in 0 until sumArr.size - 1) {
        sumArr[i + 1] += sumArr[i]
    }
    repeat(readln().toInt()) {
        val (l, r) = readln().trim().split(" ").map(String::toInt)
        val diff = r - l
        sb.appendLine(sumArr.binarySearch(diff).let { if (it >= 0) it else -it - 2 } + 1)
    }
    println(sb)
}