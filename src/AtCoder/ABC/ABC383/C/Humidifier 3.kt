package AtCoder.ABC.ABC383.C

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
val dr = intArrayOf(1, -1, 0, 0)
val dc = intArrayOf(0, 0, 1, -1)
fun main() {
    val (H, W, D) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().toCharArray() }
    val dist = Array(H) { IntArray(W) { IMPOSSIBLE } }
    val queue = ArrayDeque<Pair<Int, Int>>()
    var result = 0
    for (h in 0 until H) {
        for (w in 0 until W) {
            if (matrix[h][w] == 'H') {
                dist[h][w] = 0
                queue.add(h to w)
                result++
            }
        }
    }
    var count = 0
    while (queue.isNotEmpty() && ++count <= D) {
        repeat(queue.size) {
            val (h, w) = queue.removeFirst()
            for (d in dr.indices) {
                val nh = h + dr[d]
                val nw = w + dc[d]
                if (nh in 0 until H && nw in 0 until W && dist[nh][nw] > count && matrix[nh][nw] != '#') {
                    dist[nh][nw] = count
                    queue.add(nh to nw)
                    result++
                }
            }
        }
    }
    println(result)
}