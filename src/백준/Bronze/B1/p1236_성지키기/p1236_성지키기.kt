package 백준.Bronze.B1.p1236_성지키기

import kotlin.math.max

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").let { it[0].toInt() to it[1].toInt() }
    val rowVisited = BooleanArray(n)
    val colVisited = BooleanArray(m)
    repeat(n) { r ->
        for ((c, elm) in br.readLine().withIndex())
            if (elm == 'X') {
                rowVisited[r] = true
                colVisited[c] = true
            }
    }

    println(max(rowVisited.count { !it }, colVisited.count { !it }))
}