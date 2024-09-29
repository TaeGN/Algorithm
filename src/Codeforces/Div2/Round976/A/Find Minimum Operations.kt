package Codeforces.Div2.Round976.A

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, K) = readln().trim().split(" ").map(String::toInt)
        fun result(): Int {
            if (K == 1) return N
            val powList = mutableListOf<Int>()
            var a = 1L
            while (a <= N) {
                powList.add(a.toInt())
                a *= K
            }
            var result = 0
            var n = N
            for (i in (powList.size - 1) downTo 0) {
                if (n >= powList[i]) {
                    result += n / powList[i]
                    n %= powList[i]
                }
            }
            return result
        }
        sb.appendLine(result())
    }
    println(sb)
}