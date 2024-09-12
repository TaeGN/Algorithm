package 백준.Platinum.P4.p17412_도시왕복하기1

const val EMPTY = -1
fun main() {
    val (N, P) = readln().split(" ").map(String::toInt)
    val C = Array(N + 1) { IntArray(N + 1) }
    val F = Array(N + 1) { IntArray(N + 1) }
    repeat(P) { readln().split(" ").map(String::toInt).let { C[it[0]][it[1]]++ } }
    val pre = IntArray(N + 1) { EMPTY }
    val source = 1
    val sink = 2
    var result = 0
    while (true) {
        pre.fill(EMPTY)
        val queue = ArrayDeque<Int>()
        queue.add(source)
        while (queue.isNotEmpty()) {
            val from = queue.removeFirst()
            for (to in 1..N) {
                if (C[from][to] > F[from][to] && pre[to] == EMPTY) {
                    queue.add(to)
                    pre[to] = from
                    if (to == sink) break
                }
            }
        }
        if (pre[sink] == EMPTY) break
        var flow = Int.MAX_VALUE
        var to = sink
        while (to != source) {
            val from = pre[to]
            flow = minOf(flow, C[from][to] - F[from][to])
            to = from
        }
        to = sink
        while (to != source) {
            val from = pre[to]
            F[from][to] += flow
            F[to][from] -= flow
            to = from
        }
        result += flow
    }
    println(result)
}