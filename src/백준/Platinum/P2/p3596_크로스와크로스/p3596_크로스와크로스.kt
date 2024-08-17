package 백준.Platinum.P2.p3596_크로스와크로스

import kotlin.math.max

fun main() {
    fun result(n: Int): Int {
        val G = IntArray(n + 1).apply { this[1] = 1 }
        val set = mutableSetOf<Int>()
        for (i in 1..n) {
            set.clear()
            for (j in 1..(i + 1) / 2) {
                set.add(G[max(0, j - 3)] xor G[max(0, i - j - 2)])
            }
            for (j in 0..n) {
                if (j !in set) {
                    G[i] = j
                    break
                }
            }
        }
        return if (G[n] > 0) 1 else 2
    }
    println(result(readln().toInt()))
}