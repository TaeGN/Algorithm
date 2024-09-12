package 백준.Platinum.P3.p2316_도시왕복하기2

const val EMPTY = -1
fun main() {
    val (N, P) = readln().split(" ").map(String::toInt)
    val C = Array(2 * N + 1) { IntArray(2 * N + 1) }
    val F = Array(2 * N + 1) { IntArray(2 * N + 1) }
    for (i in 1..N) {
        C[i][i + N] = 1
    }
    repeat(P) { readln().split(" ").map(String::toInt).let { C[it[0] + N][it[1]] = 1; C[it[1] + N][it[0]] = 1 } }
    val source = 1 + N
    val sink = 2
    val pre = IntArray(2 * N + 1) { EMPTY }
    var result = 0
    while (true) {
        pre.fill(EMPTY)
        val queue = ArrayDeque<Int>()
        queue.add(source)
        while (queue.isNotEmpty()) {
            val from = queue.removeFirst()
            for (to in 1..2 * N) {
                if (C[from][to] > F[from][to] && pre[to] == EMPTY) {
                    queue.add(to)
                    pre[to] = from
                    if (to == sink) break
                }
            }
        }
        if (pre[sink] == EMPTY) break
        var to = sink
        while (to != source) {
            val from = pre[to]
            F[from][to]++
            F[to][from]--
            to = from
        }
        result++
    }
    println(result)
}