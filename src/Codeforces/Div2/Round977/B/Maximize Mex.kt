package Codeforces.Div2.Round977.B

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, X) = readln().trim().split(" ").map(String::toInt)
        val arr = IntArray(N)
        for (a in readln().trim().split(" ").map(String::toInt)) {
            if (a < N) arr[a]++
        }
        fun result(): Int {
            for (i in 0 until N) {
                if (arr[i]-- == 0) return i
                if (i + X < N) arr[i + X] += arr[i]
            }
            return N
        }
        sb.appendLine(result())
    }
    println(sb)
}