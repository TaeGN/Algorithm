package AtCoder.ProblemList.Difficulty400_799.LoongTracking

fun main() {
    val (N, Q) = readln().trim().split(" ").map(String::toInt)
    val pos = mutableListOf<Pair<Int, Int>>()
    for (i in N downTo 1) {
        pos.add(i to 0)
    }
    val sb = StringBuilder()
    repeat(Q) {
        val input = readln().trim().split(" ")
        if (input[0] == "1") {
            when (input[1]) {
                "R" -> pos.add(pos.last().let { (it.first + 1) to it.second })
                "L" -> pos.add(pos.last().let { (it.first - 1) to it.second })
                "U" -> pos.add(pos.last().let { it.first to (it.second + 1) })
                "D" -> pos.add(pos.last().let { it.first to (it.second - 1) })
            }
        } else sb.appendLine(pos[pos.size - input[1].toInt()].let { "${it.first} ${it.second}" })
    }
    println(sb)
}