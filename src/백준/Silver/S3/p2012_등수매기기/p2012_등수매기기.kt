package 백준.Silver.S3.p2012_등수매기기

import kotlin.math.abs

fun main() {
    val N = readln().toInt()
    val arr = IntArray(N) { readln().toInt() }
    arr.sort()
    println(arr.foldIndexed(0L) { index, acc, i -> acc + abs(i - index - 1) })
}