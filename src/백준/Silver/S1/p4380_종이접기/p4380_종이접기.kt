package 백준.Silver.S1.p4380_종이접기

fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val S = readln()
        fun result(): String {
            var center = S.length / 2
            while (center != 0) {
                for (i in 1..center) {
                    if (S[center - i] == S[center + i]) return "NO"
                }
                center = center / 2
            }
            return "YES"
        }
        sb.appendLine(result())
    }
    println(sb)
}