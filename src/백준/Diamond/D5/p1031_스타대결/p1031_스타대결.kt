package 백준.Diamond.D5.p1031_스타대결

const val EMPTY = -1
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val size = N + M + 2
    val C = Array(size) { IntArray(size) }
    val F = Array(size) { IntArray(size) }
    val jimin = readln().split(" ").map(String::toInt)
    val hansu = readln().split(" ").map(String::toInt)

    fun result(): String {
        if (jimin.sum() != hansu.sum()) return "-1"
        val source = size - 2
        val sink = size - 1
        for ((i, count) in jimin.withIndex()) {
            C[source][i] = count
        }
        for ((i, count) in hansu.withIndex()) {
            C[i + N][sink] = count
        }
        for (i in 0 until N) {
            for (j in N until (N + M)) {
                C[i][j] = 1
            }
        }
        val pre = IntArray(size)
        var count = 0
        while (true) {
            pre.fill(EMPTY)
            val queue = ArrayDeque<Int>()
            queue.add(source)
            while (queue.isNotEmpty()) {
                val from = queue.removeFirst()
                for (to in (size - 1) downTo 0) {
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
            count++
        }

        fun change(source: Int, sink: Int) {
            pre.fill(EMPTY)
            val queue = ArrayDeque<Int>()
            queue.add(source)
            while (queue.isNotEmpty()) {
                val from = queue.removeFirst()
                for (to in (size - 1) downTo 0) {
                    if (from < source || (from == source && to < sink)) continue
                    if (C[from][to] > F[from][to] && pre[to] == EMPTY) {
                        queue.add(to)
                        pre[to] = from
                        if (to == sink) break
                    }
                }
            }
            if (pre[sink] == EMPTY) return
            F[source][sink] = 0
            F[sink][source] = 0
            var to = sink
            while (to != source) {
                val from = pre[to]
                F[from][to]++
                F[to][from]--
                to = from
            }
            count++
        }
        if (count != jimin.sum()) return "-1"
        for (i in 0 until N) {
            for (j in N until (N + M)) {
                if (F[i][j] == 1) change(i, j)
            }
        }
        val sb = StringBuilder()
        for (i in 0 until N) {
            for (j in N until (N + M)) {
                sb.append(F[i][j])
            }
            sb.appendLine()
        }
        return sb.toString()
    }
    println(result())
}