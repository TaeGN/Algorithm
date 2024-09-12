package 백준.Gold.G3.p6086_최대유량

const val EMPTY = -1
fun main() {
    val capacity = Array(52) { IntArray(52) }
    val flow = Array(52) { IntArray(52) }
    val N = readln().toInt()
    repeat(N) {
        readln().split(" ").let {
            val fromIdx = it[0].first().let { e -> if (e.isUpperCase()) e - 'A' + 26 else e - 'a' }
            val toIdx = it[1].first().let { e -> if (e.isUpperCase()) e - 'A' + 26 else e - 'a' }
            val value = it[2].toInt()
            capacity[fromIdx][toIdx] += value
            capacity[toIdx][fromIdx] += value
        }
    }

    val start = 26
    val end = 51
    val pre = IntArray(52)
    val queue = ArrayDeque<Int>()
    var result = 0
    while (true) {
        pre.fill(EMPTY)
        queue.add(start)
        while (queue.isNotEmpty()) {
            val from = queue.removeFirst()
            for (to in 0 until 52) {
                if (capacity[from][to] > flow[from][to] && pre[to] == EMPTY) {
                    pre[to] = from
                    queue.add(to)
                    if (to == end) break
                }
            }
        }
        if (pre[end] == EMPTY) break
        var cur = end
        var minFlow = Int.MAX_VALUE
        while (cur != start) {
            minFlow = minOf(minFlow, capacity[pre[cur]][cur] - flow[pre[cur]][cur])
            cur = pre[cur]
        }
        cur = end
        while (cur != start) {
            flow[pre[cur]][cur] += minFlow
            flow[cur][pre[cur]] -= minFlow
            cur = pre[cur]
        }
        result += minFlow
    }
    println(result)
}