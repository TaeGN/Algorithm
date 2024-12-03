package AtCoder.ProblemList.Difficulty0_399.ArithmeticProgression

fun main() {
    val (A, B, D) = readln().trim().split(" ").map(String::toInt)
    val sb = StringBuilder()
    for (i in A..B step D) {
        sb.append("$i ")
    }
    println(sb)
}