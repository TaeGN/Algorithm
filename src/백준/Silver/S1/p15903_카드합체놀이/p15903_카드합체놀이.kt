package 백준.Silver.S1.p15903_카드합체놀이

import java.util.PriorityQueue

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val pq = PriorityQueue<Long>()
    readln().split(" ").map(String::toInt).forEach { pq.add(it.toLong()) }
    repeat(M) { (pq.poll() + pq.poll()).let { pq.add(it);pq.add(it) } }
    println(pq.sum())
}