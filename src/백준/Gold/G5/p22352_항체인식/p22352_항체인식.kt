package 백준.Gold.G5.p22352_항체인식

import java.io.StreamTokenizer

val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)
fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int {
        nextToken()
        return nval.toInt()
    }

    fun List<IntArray>.change(r: Int, c: Int, before: Int, after: Int) {
        this[r][c] = after
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr in indices && nc in this[0].indices && this[nr][nc] == before) {
                change(nr, nc, before, after)
            }
        }
    }

    fun List<IntArray>.isSame(other: List<IntArray>): Boolean {
        for (r in indices) {
            for (c in this[r].indices) {
                if (this[r][c] != other[r][c]) return false
            }
        }
        return true
    }

    val N = nextInt()
    val M = nextInt()
    val prevMap = List(N) { IntArray(M) }
    repeat(N) { r ->
        repeat(M) { c ->
            prevMap[r][c] = nextInt()
        }
    }

    val nextMap = List(N) { IntArray(M) }
    var isChanged = false
    repeat(N) { r ->
        repeat(M) { c ->
            nextMap[r][c] = nextInt()
            if (prevMap[r][c] != nextMap[r][c] && !isChanged) {
                prevMap.change(r, c, prevMap[r][c], nextMap[r][c])
                isChanged = true
            }
        }
    }

    println(if (prevMap.isSame(nextMap)) "YES" else "NO")
}