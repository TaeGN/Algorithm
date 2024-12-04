package AtCoder.ProblemList.Difficulty0_399.Append

fun main() {
    val list = mutableListOf<Int>()
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (x, y) = readln().trim().split(" ").map(String::toInt)
        if (x == 1) list.add(y)
        else sb.appendLine(list[list.size - y])
    }
    println(sb)
}