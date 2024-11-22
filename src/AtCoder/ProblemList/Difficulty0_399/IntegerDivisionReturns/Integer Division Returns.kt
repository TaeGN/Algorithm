package AtCoder.ProblemList.Difficulty0_399.IntegerDivisionReturns

fun main() {
    println(readln().trim().toLong().let { it / 10 + (if (it % 10 != 0L && it >= 0) 1 else 0) })
}