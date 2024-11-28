package AtCoder.ProblemList.Difficulty0_399.AdjacencyMatrix

fun main() {
    println(List(readln().trim().toInt()) {
        readln().trim().split(" ").withIndex().filter { it.value == "1" }.map { it.index + 1 }.joinToString(" ")
    }.joinToString("\n"))
}