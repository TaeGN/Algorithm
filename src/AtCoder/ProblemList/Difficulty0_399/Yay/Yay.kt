package AtCoder.ProblemList.Difficulty0_399.Yay

fun main() {
    val count = IntArray(26)
    val S = readln().trim()
    S.forEach { count[it - 'a']++ }
    println(S.indexOf('a' + count.indexOf(1)) + 1)
}