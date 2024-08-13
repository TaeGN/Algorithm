package 백준.Platinum.P5.p3197_백조의호수

import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

const val IMPOSSIBLE = Int.MAX_VALUE
val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)

fun main() {
    val st = StringTokenizer(readln())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val matrix = Array(R) { BooleanArray(C) }
    val dp = Array(R) { IntArray(C) { IMPOSSIBLE } }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val birds = mutableListOf<Int>()

    fun getHash(r: Int, c: Int) = r * C + c
    fun getR(hash: Int) = hash / C
    fun getC(hash: Int) = hash % C
    fun isValid(r: Int, c: Int) = r in 0 until R && c in 0 until C

    for (r in 0 until R) {
        val input = readln()
        for (c in 0 until C) {
            matrix[r][c] = input[c] == 'X'
            when (input[c]) {
                'X' -> continue
                'L' -> birds.add(getHash(r, c))
            }
            pq.add(getHash(r, c) to 0)
            dp[r][c] = 0
        }
    }

    while (pq.isNotEmpty()) {
        val (hash, time) = pq.poll()
        val r = getR(hash)
        val c = getC(hash)
        if (dp[r][c] < time) continue
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (isValid(nr, nc) && dp[nr][nc] > time + 1) {
                pq.add(getHash(nr, nc) to (time + 1))
                dp[nr][nc] = time + 1
            }
        }
    }

    var result = -1
    pq.add(birds[0] to 0)
    while (pq.isNotEmpty()) {
        val (hash, time) = pq.poll()
        if (hash == birds[1]) {
            result = time
            break
        }
        val r = getR(hash)
        val c = getC(hash)
        for (d in dr.indices) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (isValid(nr, nc) && dp[nr][nc] != IMPOSSIBLE) {
                pq.add(getHash(nr, nc) to max(time, dp[nr][nc]))
                dp[nr][nc] = IMPOSSIBLE
            }
        }
    }

    println(result)
}
