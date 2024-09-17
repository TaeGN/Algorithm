package 백준.Gold.G5.p1911_흙길보수하기

fun main() {
    val (N, L) = readln().split(" ").map(String::toInt)
    val list = Array(N) { readln().split(" ").map(String::toInt) }.sortedBy { it[0] }
    var prevR = -1
    var totalCount = 0
    for ((l, r) in list) {
        val curR = r - 1
        if (curR <= prevR) continue
        val curL = maxOf(prevR + 1, l)
        val count = (curR - curL) / L + 1
        prevR = curL + count * L - 1
        totalCount += count
    }
    println(totalCount)
}