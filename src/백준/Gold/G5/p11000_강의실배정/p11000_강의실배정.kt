package 백준.Gold.G5.p11000_강의실배정

import java.util.PriorityQueue

fun main() {
    val N = readln().toInt()
    val room = PriorityQueue<Int>()
    val lecture = Array(N) { readln().split(" ").map(String::toInt) }.sortedWith(compareBy({ it[0] }, { it[1] }))
    for ((S, T) in lecture) {
        if (room.isNotEmpty() && room.peek() <= S) room.poll()
        room.add(T)
    }
    println(room.size)
}