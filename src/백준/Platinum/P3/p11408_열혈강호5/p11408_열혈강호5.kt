package 백준.Platinum.P3.p11408_열혈강호5

const val EMPTY = -1
const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val size = N + M + 2
    val C = Array(size) { IntArray(size) }
    val F = Array(size) { IntArray(size) }
    val D = Array(size) { IntArray(size) }
    repeat(N) { empId ->
        val input = readln().split(" ").map(String::toInt)
        repeat(input[0]) { j ->
            val jobId = input[j * 2 + 1] - 1 + N
            val salary = input[j * 2 + 2]
            C[empId][jobId] = 1
            D[empId][jobId] = salary
            D[jobId][empId] = -salary
        }
    }

    val source = size - 2
    val sink = size - 1
    for (i in 0 until N) {
        C[source][i] = 1
    }
    for (i in N until (N + M)) {
        C[i][sink] = 1
    }
    val pre = IntArray(size)
    val dist = IntArray(size)
    val queue = ArrayDeque<Int>()
    val isInQ = BooleanArray(size)
    fun add(id: Int) {
        if (isInQ[id]) return
        isInQ[id] = true
        queue.add(id)
    }

    fun poll() = queue.removeFirst().also { isInQ[it] = false }
    var count = 0
    var cost = 0
    while (true) {
        pre.fill(EMPTY)
        dist.fill(IMPOSSIBLE)
        dist[source] = 0
        queue.clear()
        queue.add(source)
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
        var to = sink
        while (to != source) {
            val from = pre[to]
            cost += D[from][to]
            F[from][to]++
            F[to][from]--
            to = from
        }
        count++
    }
    println("$count\n$cost")
}