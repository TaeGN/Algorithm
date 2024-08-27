package 백준.Gold.G1.p4991_로봇청소기

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
val dr = intArrayOf(0, 0, 1, -1)
val dc = intArrayOf(1, -1, 0, 0)
fun main() {
    while (true) {
        val (W, H) = readln().split(" ").map(String::toInt)
        if (W == 0 && H == 0) break
        fun hash(r: Int, c: Int) = r * W + c
        val matrix = Array(H) { readln().toCharArray() }
        val list = mutableListOf<Pair<Int, Int>>()
        matrix.forEachIndexed { r, chars -> chars.forEachIndexed { c, elm -> if (elm == 'o') list.add(r to c) } }
        matrix.forEachIndexed { r, chars -> chars.forEachIndexed { c, elm -> if (elm == '*') list.add(r to c) } }
        val map = list.mapIndexed { index, (r, c) -> hash(r, c) to index }.toMap()
        val distMatrix = Array(list.size) { IntArray(list.size) { IMPOSSIBLE } }
        val visited = Array(H) { BooleanArray(W) }
        fun bfs(sr: Int, sc: Int) {
            val fromIdx = map[hash(sr, sc)]!!
            visited.forEach { it.fill(false) }
            val queue = ArrayDeque<Pair<Int, Int>>()
            queue.add(sr to sc)
            visited[sr][sc] = true
            var count = 0
            while (queue.isNotEmpty()) {
                count++
                repeat(queue.size) {
                    val (r, c) = queue.removeFirst()
                    for (d in dr.indices) {
                        val nr = r + dr[d]
                        val nc = c + dc[d]
                        if (nr in 0 until H && nc in 0 until W && !visited[nr][nc] && matrix[nr][nc] != 'x') {
                            visited[nr][nc] = true
                            queue.add(nr to nc)
                            if (hash(nr, nc) in map) {
                                val toIdx = map[hash(nr, nc)]!!
                                distMatrix[fromIdx][toIdx] = count
                                distMatrix[toIdx][fromIdx] = count
                            }
                        }
                    }
                }
            }
        }
        list.forEach { bfs(it.first, it.second) }
        var result = IMPOSSIBLE
        fun permutation(flag: Int = 1, idxArr: IntArray = IntArray(list.size).apply { this[0] = 0 }) {
            if (flag == (1 shl list.size) - 1) {
                var totalDist = 0
                for (i in 0 until (list.size - 1)) {
                    val dist = distMatrix[idxArr[i]][idxArr[i + 1]]
                    if (dist == IMPOSSIBLE) return
                    totalDist += dist
                }
                result = minOf(result, totalDist)
                return
            }
            for (i in 1 until list.size) {
                if ((flag and (1 shl i)) != 0) continue
                idxArr[flag.countOneBits()] = i
                permutation(flag or (1 shl i), idxArr)
            }
        }
        permutation()
        println(if (result == IMPOSSIBLE) -1 else result)
    }
}