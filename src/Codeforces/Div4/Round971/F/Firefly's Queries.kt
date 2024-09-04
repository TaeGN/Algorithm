package Codeforces.Div4.Round971.F

fun main() {
    repeat(readln().toInt()) {
        val (N, Q) = readln().trim().split(" ").map(String::toInt)
        val aArr = readln().trim().split(" ").map(String::toInt)
        val sumArr = LongArray(2 * N - 1)
        for (i in 0 until 2 * N - 1) {
            sumArr[i] = (if (i > 0) sumArr[i - 1] else 0) + aArr[i % N]
        }
        repeat(Q) {
            val (l, r) = readln().trim().split(" ").map(String::toLong)
            val (x1, y1) = (l - 1).let { (it / N).toInt() to (it % N).toInt() }
            val (x2, y2) = (r - 1).let { (it / N).toInt() to (it % N).toInt() }
            var result = sumArr[N - 1] * (x2 - x1) + sumArr[x2 + y2]
            if (x2 > 0) result -= sumArr[x2 - 1]
            if (x1 + y1 > 0) result -= sumArr[x1 + y1 - 1]
            if (x1 > 0) result += sumArr[x1 - 1]
            println(result)
        }
    }
}