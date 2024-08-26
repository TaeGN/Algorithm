package Codeforces.Div2.Round968.D1

fun main() {
    val sb = StringBuilder()
    val T = readln().toInt()
    val selected = BooleanArray(200010)
    repeat(T) {
        val (N, M) = readln().split(" ").map(String::toInt)
        var maxMex = 0
        repeat(N) {
            val arr = readln().split(" ").map(String::toInt)
            val size = minOf(selected.size, arr[0] + 2)
            selected.fill(false, 0, size)
            for (i in 1..arr[0]) {
                if (arr[i] < size) selected[arr[i]] = true
            }
            selected[selected.indexOf(false)] = true
            maxMex = maxOf(maxMex, selected.indexOf(false))
        }
        val result =
            (minOf(M, maxMex) + 1).toLong() * maxMex + (M - minOf(M, maxMex)).toLong() * (M + minOf(M, maxMex) + 1) / 2
        sb.appendLine(result)
    }
    println(sb)
}