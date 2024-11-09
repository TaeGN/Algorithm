package AtCoder.ABC.ABC379.D

fun main() {
    val days = mutableListOf(0L)
    val queue = ArrayDeque<Pair<Int, Int>>()
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val input = readln().trim().split(" ").map(String::toInt)
        when (input[0]) {
            1 -> if (queue.isEmpty() || queue.last().first != days.size) queue.add((days.size - 1) to 1)
            else queue.add(queue.removeLast().let { it.first to (it.second + 1) })

            2 -> days.add(days.last() + input[1])

            3 -> {
                var count = 0
                while (queue.isNotEmpty() && (days.last() - days[queue.first().first]) >= input[1]) {
                    queue.removeFirst()
                    count++
                }
                sb.appendLine(count)
            }
        }
    }
    println(sb)
}