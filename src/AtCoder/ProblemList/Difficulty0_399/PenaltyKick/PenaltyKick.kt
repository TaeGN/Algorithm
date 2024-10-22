package AtCoder.ProblemList.Difficulty0_399.PenaltyKick

fun main() {
    val N = readln().toInt()
    val word = "oox"
    println(word.repeat(N / 3) + word.substring(0, N % 3))
}