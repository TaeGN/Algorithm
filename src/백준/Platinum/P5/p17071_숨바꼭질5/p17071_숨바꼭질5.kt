package 백준.Platinum.P5.p17071_숨바꼭질5

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val EVEN = 0
fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val dp = Array(2) { IntArray(500001) { IMPOSSIBLE } }
    val queue = ArrayDeque<Int>()
    val visited = Array(2) { BooleanArray(500001) }
    fun add(time: Int, value: Int) {
        val type = time % 2
        if (value in 0..500000 && !visited[type][value]) {
            queue.add(value)
            visited[type][value] = true
            dp[type][value] = time
        }
    }

    var t = 0
    add(EVEN, N)
    while (queue.isNotEmpty()) {
        t++
        repeat(queue.size) {
            val n = queue.removeFirst()
            add(t, n + 1)
            add(t, n - 1)
            add(t, n * 2)
        }
    }

    fun result(): Int {
        var time = 0
        while (K + time * (time + 1) / 2 <= 500000) {
            val type = time % 2
            if (dp[type][K + time * (time + 1) / 2] <= time) return time
            time++
        }
        return -1
    }
    println(result())
}