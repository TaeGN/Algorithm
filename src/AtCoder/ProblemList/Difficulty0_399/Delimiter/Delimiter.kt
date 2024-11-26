package AtCoder.ProblemList.Difficulty0_399.Delimiter

fun main() {
    val list = mutableListOf<Int>()
    try {
        while (true) list.add(readln().trim().toInt())
    } catch (e: Exception) {
        println(list.reversed().joinToString("\n"))
    }
}