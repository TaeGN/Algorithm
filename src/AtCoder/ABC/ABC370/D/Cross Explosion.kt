package AtCoder.ABC.ABC370.D

import java.util.TreeSet
fun main() {
    val (H, W, Q) = readln().trim().split(" ").map(String::toInt)
    val rcMatrix = Array(H + 1) { TreeSet<Int>() }
    val crMatrix = Array(W + 1) { TreeSet<Int>() }
    for (r in 1..H) {
        for (c in 1..W) {
            rcMatrix[r].add(c)
            crMatrix[c].add(r)
        }
    }
    repeat(Q) {
        val (R, C) = readln().trim().split(" ").map(String::toInt)
        if (C in rcMatrix[R]) {
            rcMatrix[R].remove(C)
            crMatrix[C].remove(R)
        } else {
            rcMatrix[R].higher(C)?.let { rcMatrix[R].remove(it); crMatrix[it].remove(R) }
            rcMatrix[R].lower(C)?.let { rcMatrix[R].remove(it); crMatrix[it].remove(R) }
            crMatrix[C].higher(R)?.let { rcMatrix[it].remove(C); crMatrix[C].remove(it) }
            crMatrix[C].lower(R)?.let { rcMatrix[it].remove(C); crMatrix[C].remove(it) }
        }
    }
    println(rcMatrix.sumOf { it.size })
}