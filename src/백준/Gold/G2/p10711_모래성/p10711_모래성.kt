package 백준.Gold.G2.p10711_모래성

val dr = intArrayOf(0, 0, 1, -1, 1, 1, -1, -1)
val dc = intArrayOf(1, -1, 0, 0, 1, -1, 1, -1)
fun main() {
    val (H, W) = readln().trim().split(" ").map(String::toInt)
    val matrix = Array(H) { readln().trim().map { if (it == '.') 0 else it.digitToInt() }.toIntArray() }
    var set = mutableSetOf<Int>()
    fun isValid(r: Int, c: Int) = r in 0 until H && c in 0 until W
    fun hash(r: Int, c: Int) = r * W + c
    fun rc(hash: Int) = hash / W to hash % W
    fun count(r: Int, c: Int): Int {
        var count = 0
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (isValid(nr, nc) && matrix[nr][nc] == 0) count++
        }
        return count
    }

    fun isNotAlive(r: Int, c: Int) = matrix[r][c] <= count(r, c)
    for (r in 0 until H) {
        for (c in 0 until W) {
            if (matrix[r][c] > 0 && isNotAlive(r, c)) set.add(hash(r, c))
        }
    }
    var result = 0
    var nSet = mutableSetOf<Int>()
    while (set.isNotEmpty()) {
        result++
        set.forEach { matrix[it / W][it % W] = 0 }
        for (hash in set) {
            val (r, c) = rc(hash)
            for (d in dr.indices) {
                val nr = r + dr[d]
                val nc = c + dc[d]
                if (isValid(nr, nc) && matrix[nr][nc] > 0 && isNotAlive(nr, nc)) nSet.add(hash(nr, nc))
            }
        }
        set.clear()
        val temp = set
        set = nSet
        nSet = temp
    }
    println(result)
}