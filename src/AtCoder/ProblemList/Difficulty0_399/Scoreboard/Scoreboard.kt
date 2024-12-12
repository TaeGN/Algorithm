package AtCoder.ProblemList.Difficulty0_399.Scoreboard

fun main() {
    var score = 0
    repeat(readln().trim().toInt()) {
        score += readln().trim().split(" ").map(String::toInt).let { it[0] - it[1] }
    }
    println(if (score > 0) "Takahashi" else if (score < 0) "Aoki" else "Draw")
}