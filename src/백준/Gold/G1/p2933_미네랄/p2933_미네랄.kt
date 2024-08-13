package 백준.Gold.G1.p2933_미네랄

import java.util.StringTokenizer

val dr = intArrayOf(1, 0, 0, -1)
val dc = intArrayOf(0, 1, -1, 0)

fun main() {
    var st = StringTokenizer(readln())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val matrix = Array(R) { BooleanArray(C) }
    repeat(R) { r ->
        val input = readln()
        repeat(C) { c -> matrix[r][c] = input[c] == 'x' }
    }

    fun getHash(r: Int, c: Int) = r * C + c
    fun getRC(hash: Int) = (hash / C) to (hash % C)
    fun isValid(r: Int, c: Int) = r in 0 until R && c in 0 until C

    val queue = ArrayDeque<Int>()
    val setList = List(4) { mutableSetOf<Int>() }
    fun Array<BooleanArray>.remove(r: Int, c: Int) {
        this[r][c] = false
        for (i in 0 until 4) {
            val sr = r + dr[i]
            val sc = c + dc[i]
            val sHash = getHash(sr, sc)
            if (!isValid(sr, sc) || !matrix[sr][sc]) continue
            for (j in 0 until i) {
                if (sHash in setList[j]) continue
            }
            queue.clear()
            setList[i].clear()
            queue.add(sHash)
            setList[i].add(sHash)
            var inTheAir = true
            while (queue.isNotEmpty()) {
                val (cr, cc) = getRC(queue.removeFirst())
                if (cr == R - 1) inTheAir = false
                for (d in dr.indices) {
                    val nr = cr + dr[d]
                    val nc = cc + dc[d]
                    val nHash = getHash(nr, nc)
                    if (isValid(nr, nc) && matrix[nr][nc] && setList[i].add(nHash)) {
                        queue.add(nHash)
                    }
                }
            }

            if (inTheAir) {
                val len = setList[i].minOf {
                    it.let {
                        val (cr, cc) = getRC(it)
                        var er = cr
                        while (er + 1 < R && (!matrix[er + 1][cc] || getHash(er + 1, cc) in setList[i])) er++
                        er - cr + 1
                    }
                }
                for (hash in setList[i]) {
                    val (cr, cc) = getRC(hash)
                    matrix[cr][cc] = false
                }
                for (hash in setList[i]) {
                    val (cr, cc) = getRC(hash)
                    matrix[cr + len - 1][cc] = true
                }
            }
        }
    }

    val N = readln().toInt()
    st = StringTokenizer(readln())
    repeat(N) { idx ->
        val h = st.nextToken().toInt()
        val r = R - h
        if (idx % 2 == 0) {
            for (c in 0 until C) {
                if (matrix[r][c]) {
                    matrix.remove(r, c)
                    break
                }
            }
        } else {
            for (c in (C - 1) downTo 0) {
                if (matrix[r][c]) {
                    matrix.remove(r, c)
                    break
                }
            }
        }
    }

    println(matrix.joinToString("\n") { row -> row.joinToString("") { if (it) "x" else "." } })
}