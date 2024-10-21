package AtCoder.ABC.ABC376.D

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Int>() }
    val inLists = List(N + 1) { mutableListOf<Int>() }
    repeat(M) {
        val (A, B) = readln().trim().split(" ").map(String::toInt)
        outLists[A].add(B)
        inLists[B].add(A)
    }

    fun bfs(lists: List<MutableList<Int>>): IntArray {
        val distArr = IntArray(N + 1) { IMPOSSIBLE }.apply { this[1] = 0 }
        val queue = ArrayDeque<Int>()
        queue.add(1)
        var count = 0
        while (queue.isNotEmpty()) {
            count++
            repeat(queue.size) {
                val from = queue.removeFirst()
                for (to in lists[from]) {
                    if (distArr[to] == IMPOSSIBLE) {
                        distArr[to] = count
                        queue.add(to)
                    }
                }
            }
        }
        return distArr
    }

    val dist1 = bfs(outLists)
    val dist2 = bfs(inLists)
    println((2..N).minOf { dist1[it] + dist2[it] }.let { if (it >= IMPOSSIBLE) -1 else it })
}