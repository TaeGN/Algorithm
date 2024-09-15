package 백준.Silver.S2.p11501_주식

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val N = readln().toInt()
        val list = readln().split(" ").map(String::toInt)
        val maxArr = IntArray(N + 1) { N - 1 }
        for (i in (N - 1) downTo 0) {
            if (list[maxArr[i + 1]] <= list[i]) maxArr[i] = i
            else maxArr[i] = maxArr[i + 1]
        }
        var result = 0L
        var idx = 0
        while (idx < N) {
            val nIdx = maxArr[idx]
            result += (idx until nIdx).fold(0L) { acc, i -> acc + list[nIdx] - list[i] }
            idx = nIdx + 1
        }
        sb.appendLine(result)
    }
    println(sb)
}