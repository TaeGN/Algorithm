package 백준.Gold.G4.p13913_숨바꼭질4

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val EMPTY = -1
fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val visited = BooleanArray(100001)
    val parentArr = IntArray(100001) { EMPTY }
    val queue = ArrayDeque<Int>()
    fun add(idx: Int): Boolean {
        if (idx in 0..100000 && !visited[idx]) {
            visited[idx] = true
            queue.add(idx)
            return true
        }
        return false
    }

    fun result(): Int {
        var time = 0
        add(N)
        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val cur = queue.removeFirst()
                if (cur == K) return time
                if (add(cur + 1)) parentArr[cur + 1] = cur
                if (add(cur - 1)) parentArr[cur - 1] = cur
                if (add(cur * 2)) parentArr[cur * 2] = cur
            }
            time++
        }
        return -1
    }
    println(result())
    val sb = StringBuilder()
    var idx = K
    while (idx != EMPTY) {
        sb.insert(0, "$idx ")
        idx = parentArr[idx]
    }
    println(sb)
}