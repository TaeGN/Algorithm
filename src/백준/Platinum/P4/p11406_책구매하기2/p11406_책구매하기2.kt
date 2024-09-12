package 백준.Platinum.P4.p11406_책구매하기2

const val EMPTY = -1
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val SOURCE = N + M
    val SINK = N + M + 1
    val C = Array(N + M + 2) { IntArray(N + M + 2) }
    val F = Array(N + M + 2) { IntArray(N + M + 2) }
    for ((i, count) in readln().split(" ").map(String::toInt).withIndex()) {
        C[SOURCE][i] += count
    }
    for ((i, count) in readln().split(" ").map(String::toInt).withIndex()) {
        C[i + N][SINK] += count
    }
    repeat(M) { i ->
        for ((j, count) in readln().split(" ").map(String::toInt).withIndex()) {
            C[j][i + N] += count
        }
    }
    val pre = IntArray(N + M + 2)
    val queue = ArrayDeque<Int>()
    var result = 0
    while (true) {
        queue.clear()
        pre.fill(EMPTY)
        queue.add(SOURCE)
        while (queue.isNotEmpty()) {
            val from = queue.removeFirst()
            for (to in C.indices) {
                if (C[from][to] > F[from][to] && pre[to] == EMPTY) {
                    queue.add(to)
                    pre[to] = from
                    if (to == SINK) break
                }
            }
        }
        if (pre[SINK] == EMPTY) break
        var flow = Int.MAX_VALUE
        var to = SINK
        while (to != SOURCE) {
            val from = pre[to]
            flow = minOf(flow, C[from][to] - F[from][to])
            to = from
        }
        to = SINK
        while (to != SOURCE) {
            val from = pre[to]
            F[from][to] += flow
            F[to][from] -= flow
            to = from
        }
        result += flow
    }
    println(result)
}