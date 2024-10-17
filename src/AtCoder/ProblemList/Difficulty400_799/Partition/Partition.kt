package AtCoder.ProblemList.Difficulty400_799.Partition

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    fun result(): String {
        if (K <= 0) return if (A.sumOf { it.toLong() } >= K) "Yes\n${A.sortedDescending().joinToString(" ")}" else "No"
        return "Yes\n${A.sorted().joinToString(" ")}"
    }
    println(result())
}