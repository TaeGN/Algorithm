package 백준.Bronze.B3.p27736_찬반투표

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map(String::toInt)
    (if (arr.count { it == 0 } >= (arr.size + 1) / 2) "INVALID"
    else if (arr.sum() > 0) "APPROVED"
    else "REJECTED").let(::println)
}