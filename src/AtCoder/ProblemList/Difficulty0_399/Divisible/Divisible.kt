package AtCoder.ProblemList.Difficulty0_399.Divisible

fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val sb = StringBuilder()
    readln().split(" ").map(String::toInt).forEach { if (it % K == 0) sb.append("${it / K} ") }
    println(sb)
}