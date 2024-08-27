package 백준.Silver.S3.p6312_Triangles

fun main() {
    var count = 0
    val sb = StringBuilder()
    while (true) {
        val N = readln().toInt()
        if (N == 0) break
        val matrix = Array(N) { readln().trim().toCharArray() }
        fun isPossible(n: Int): Boolean {
            val rLen = n
            val cLen = 2 * n - 1
            for (i in 0..(matrix.size - rLen)) {
                for (j in 0..(matrix[i].size - cLen)) {
                    if (j % 2 == 0) {
                        var isPossible = true
                        a@ for (r in 0 until rLen) {
                            for (c in 0 until (cLen - 2 * r)) {
                                if (matrix[r + i][c + j] == '#') {
                                    isPossible = false
                                    break@a
                                }
                            }
                        }
                        if (isPossible) return true
                    } else if (i >= rLen - 1) {
                        var isPossible = true
                        a@ for (r in 0 until rLen) {
                            for (c in 0 until (cLen - 2 * r)) {
                                if (matrix[i - r][c + j + r * 2] == '#') {
                                    isPossible = false
                                    break@a
                                }
                            }
                        }
                        if (isPossible) return true
                    }
                }
            }
            return false
        }

        fun search(start: Int = 0, end: Int = N): Int {
            val mid = (start + end) / 2
            if (start == mid) return if (isPossible(end)) end else start
            return if (isPossible(mid)) search(mid, end)
            else search(start, mid - 1)
        }
        sb.appendLine("Triangle #${++count}\nThe largest triangle area is ${search().let { it * it }}.\n")
    }
    println(sb)
}