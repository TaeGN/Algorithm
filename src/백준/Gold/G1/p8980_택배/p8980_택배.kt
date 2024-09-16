package 백준.Gold.G1.p8980_택배

fun main() {
    val (N, C) = readln().split(" ").map(String::toInt)
    val cArr = IntArray(N + 1) { C }
    val inComeLists = List(N + 1) { mutableListOf<Pair<Int, Int>>() }
    repeat(readln().toInt()) { readln().split(" ").map(String::toInt).let { inComeLists[it[1]].add(it[0] to it[2]) } }
    inComeLists.forEach { list -> list.sortBy { -it.first } }
    var result = 0
    for (i in 2..N) {
        for ((j, c) in inComeLists[i]) {
            var value = c
            for (k in j until i) {
                value = minOf(cArr[k], value)
            }
            for (k in j until i) {
                cArr[k] -= value
            }
            result += value
        }
    }
    println(result)
}