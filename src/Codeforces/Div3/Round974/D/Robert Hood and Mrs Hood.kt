package Codeforces.Div3.Round974.D


fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, D, K) = readln().split(" ").map(String::toInt)
        val startArr = IntArray(N)
        val endArr = IntArray(N)
        repeat(K) {
            val (L, R) = readln().split(" ").map { it.toInt() - 1 }
            startArr[L]++
            endArr[R]++
        }
        var count = 0
        var min = Int.MAX_VALUE
        var max = 0
        var minIdx = 0
        var maxIdx = 0
        for (i in 0 until N) {
            if (i >= D) count -= endArr[i - D]
            count += startArr[i]
            if (i >= D - 1) {
                if (min > count) {
                    min = count
                    minIdx = i - D + 2
                }
                if (max < count) {
                    max = count
                    maxIdx = i - D + 2
                }
            }
        }
        sb.appendLine("$maxIdx $minIdx")
    }
    println(sb)
}