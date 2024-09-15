package 백준.Silver.S4.p2217_로프

fun main() {
    val N = readln().toInt()
    val wArr = Array(N) { readln().toInt() }.sortedDescending()
    var result = 0
    for (i in 0 until N) {
        result = maxOf(result, (i + 1) * wArr[i])
    }
    println(result)
}