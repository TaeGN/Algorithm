package AtCoder.ProblemList.Difficulty400_799.SortLeftAndRight

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val N = readln().trim().toInt()
        val P = readln().trim().split(" ").map(String::toInt).toIntArray()
        fun result(): Int {
            if (P.first() == N && P.last() == 1) return 3
            if (P.contentEquals(IntArray(N) { it + 1 })) return 0
            var max = 0
            for (i in 1..N) {
                if (P[i - 1] == i && max == (i - 1)) return 1
                max = maxOf(max, P[i - 1])
            }
            return 2
        }
        sb.appendLine(result())
    }
    println(sb)
}