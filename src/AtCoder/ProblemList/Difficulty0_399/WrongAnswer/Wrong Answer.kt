package AtCoder.ProblemList.Difficulty0_399.WrongAnswer

fun main() {
    println(readln().trim().split(" ").map(String::toInt).sum().let { if (it == 0) 1 else 0 })
}