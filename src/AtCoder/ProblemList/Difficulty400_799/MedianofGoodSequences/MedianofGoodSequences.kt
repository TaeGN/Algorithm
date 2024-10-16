package AtCoder.ProblemList.Difficulty400_799.MedianofGoodSequences

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    fun result(): String {
        val sb = StringBuilder()
        if (N == 1) repeat(K) { sb.append("1 ") }
        else if (N % 2 == 0) {
            sb.append("${N / 2} ")
            for (i in N downTo 1) {
                repeat(if (i == N / 2) K - 1 else K) { sb.append("$i ") }
            }
        } else {
            repeat(K) { sb.append("${(N + 1) / 2} ") }
            sb.append("${N / 2} ")
            for (i in N downTo 1) {
                if (i == (N + 1) / 2) continue
                repeat(if (i == N / 2) K - 1 else K) { sb.append("$i ") }
            }
        }
        return sb.toString()
    }
    println(result())
}