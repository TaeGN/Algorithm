package 백준.Silver.S3.p12018_YonseiTOTO

import java.util.PriorityQueue

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val pq = PriorityQueue<Int>()
    repeat(N) {
        val (P, L) = readln().trim().split(" ").map(String::toInt)
        val list = readln().trim().split(" ").map(String::toInt).sortedDescending()
        pq.add(if (P < L) 1 else list[L - 1])
    }
    var result = 0
    var remainedM = M
    while (pq.isNotEmpty() && remainedM >= pq.peek()) {
        remainedM -= pq.poll()
        result++
    }
    println(result)
}