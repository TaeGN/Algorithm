package 백준.Gold.G5.p2212_센서

fun main() {
    val N = readln().toInt()
    val K = readln().toInt()
    val list = readln().split(" ").map(String::toInt).sorted()
    val diffArr = IntArray(N - 1)
    for (i in 0 until N - 1) {
        diffArr[i] = list[i + 1] - list[i]
    }
    diffArr.sortDescending()
    var result = list.last() - list.first()
    for (i in 0 until minOf(N - 1, K - 1)) {
        result -= diffArr[i]
    }
    println(result)
}