package AtCoder.ProblemList.Difficulty400_799.DiversityOfScores

fun main() {
    val (N, T) = readln().trim().split(" ").map(String::toInt)
    val scoreArr = LongArray(N + 1)
    val scoreMap = mutableMapOf(0L to N)
    val sb = StringBuilder()
    repeat(T) {
        val (A, B) = readln().trim().split(" ").map(String::toInt)
        val prev = scoreArr[A]
        val next = prev + B
        scoreArr[A] = next
        scoreMap.compute(prev) { _, v -> if (v == null || v == 1) null else v - 1 }
        scoreMap.compute(next) { _, v -> if (v == null) 1 else v + 1 }
        sb.appendLine(scoreMap.size)
    }
    println(sb)
}