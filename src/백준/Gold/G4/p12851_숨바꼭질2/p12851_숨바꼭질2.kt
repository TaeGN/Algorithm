package 백준.Gold.G4.p12851_숨바꼭질2

const val MAX_N = 100_000
const val MAX_K = 100_000
fun main() {
    val (n, k) = readln().split(" ").let { it[0].toInt() to it[1].toInt() }
    fun List<IntArray>.visit(pos: Int, time: Int, weight: Int): Boolean = try {
        if (this[pos][0] > time) {
            this[pos][0] = time
            this[pos][1] = weight
            true
        } else {
            if (this[pos][0] == time) this[pos][1] += weight
            false
        }
    } catch (e: IndexOutOfBoundsException) {
        false
    }

    fun Int.hideAndSeek(other: Int): Pair<Int, Int> {
        val visitedList =
            List(this + other + 1) { pos -> intArrayOf(if (pos == this) 0 else Int.MAX_VALUE, 1) }
        val queue = ArrayDeque<Int>()
        queue.add(this)
        var time = 0
        while (queue.isNotEmpty()) {
            time++
            repeat(queue.size) {
                val pos = queue.removeFirst()
                if (pos == other) return visitedList[other].let { it[0] to it[1] }
                if (visitedList.visit(pos * 2, time, visitedList[pos][1])) queue.add(pos * 2)
                if (visitedList.visit(pos + 1, time, visitedList[pos][1])) queue.add(pos + 1)
                if (visitedList.visit(pos - 1, time, visitedList[pos][1])) queue.add(pos - 1)
            }
        }

        return visitedList[other].let { it[0] to it[1] }
    }

    n.hideAndSeek(k).let { println("${it.first}\n${it.second}") }
}