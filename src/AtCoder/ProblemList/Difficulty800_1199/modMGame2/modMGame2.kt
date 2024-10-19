package AtCoder.ProblemList.Difficulty800_1199.modMGame2


fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (N, M) = readln().trim().split(" ").map(String::toInt)
        sb.appendLine(if ((N.toLong() * (N + 1) % M) in 1..N) "Bob" else "Alice")
    }
    println(sb)
}