package 백준.Platinum.P3.p11405_책구매하기

const val EMPTY = -1
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val size = N + M + 2
    val C = Array(size) { IntArray(size) }
    val F = Array(size) { IntArray(size) }
    val D = Array(size) { IntArray(size) }
    val source = size - 2
    val sink = size - 1
    for ((i, count) in readln().split(" ").map(String::toInt).withIndex()) {
        C[source][i] += count
    }
    for ((i, count) in readln().split(" ").map(String::toInt).withIndex()) {
        C[i + N][sink] += count
    }
    for (i in N until (N + M)) {
        for (j in 0 until N) {
            C[j][i] = C[i][sink]
        }
    }
    repeat(M) { i ->
        for ((j, cost) in readln().split(" ").map(String::toInt).withIndex()) {
            D[j][i + N] = cost
            D[i + N][j] = -cost
        }
    }
    var result = 0
    val pre = IntArray(size)
    val dist = IntArray(size)
    val isInQ = BooleanArray(size)
    fun init() {
        pre.fill(EMPTY)
        dist.fill(IMPOSSIBLE)
        dist[source] = 0
    }
    while (true) {
        init()
        val queue = ArrayDeque<Int>()
        fun add(id: Int) {
            if (isInQ[id]) return
            isInQ[id] = true
            queue.add(id)
        }

        fun poll() = queue.removeFirst().also { isInQ[it] = false }

        add(source)
        while (queue.isNotEmpty()) {
            val from = poll()
            for (to in 0 until size) {
                if (C[from][to] > F[from][to] && dist[to] > dist[from] + D[from][to]) {
                    dist[to] = dist[from] + D[from][to]
                    pre[to] = from
                    add(to)
                }
            }
        }
        if (pre[sink] == EMPTY) break
        var flow = IMPOSSIBLE
        var to = sink
        while (to != source) {
            val from = pre[to]
            flow = minOf(flow, C[from][to] - F[from][to])
            to = from
        }
        to = sink
        while (to != source) {
            val from = pre[to]
            result += flow * D[from][to]
            F[from][to] += flow
            F[to][from] -= flow
            to = from
        }
    }
    println(result)
}