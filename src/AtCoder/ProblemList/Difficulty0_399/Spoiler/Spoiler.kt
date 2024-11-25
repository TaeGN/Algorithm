package AtCoder.ProblemList.Difficulty0_399.Spoiler

fun main() {
    val S = readln().trim()
    println(S.substring(0, S.indexOfFirst { it == '|' }) + S.substring(S.indexOfLast { it == '|' } + 1))
}