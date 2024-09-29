package Codeforces.Div2.Round976.C

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (A, B, C) = readln().trim().split(" ").map(String::toLong)
        fun result(): Long {
            var sum = A - B - C
            if (sum > 0) return -1
            var a = 0L
            for (i in 61 downTo 0) {
                val aa = A and (1L shl i)
                val bb = B and (1L shl i)
                if (aa == 0L && bb != 0L) sum += (1L shl i)
                else if (aa == 0L) {
                    if ((1L shl i) + sum <= 0) {
                        sum += (1L shl i)
                        a += (1L shl i)
                    }
                } else if (bb != 0L) {
                    if ((1L shl i) + sum <= 0) {
                        sum += (1L shl i)
                    } else a += (1L shl i)
                }
            }
            return if (sum == 0L) a else -1
        }
        sb.appendLine(result())
    }
    println(sb)
}