package AtCoder.ProblemList.Difficulty400_799.NoAttacking

fun main() {
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (N, A, B) = readln().trim().split(" ").map(String::toInt)
        sb.appendLine(if (N >= A && B <= (N - A) * minOf((N + 1) / 2, (N - A))) "Yes" else "No")
    }
    println(sb)
}