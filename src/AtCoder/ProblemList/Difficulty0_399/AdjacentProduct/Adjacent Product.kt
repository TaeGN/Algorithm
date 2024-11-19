package AtCoder.ProblemList.Difficulty0_399.AdjacentProduct

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val B = (0 until (N - 1)).map { A[it] * A[it + 1] }
    println(B.joinToString(" "))
}