package 백준.Silver.S4.p2847_게임을만든동준이

fun main() {
    val N = readln().toInt()
    val arr = Array(N) { readln().toInt() }
    var result = 0
    for (i in (N - 2) downTo 0) {
        result += maxOf(0, arr[i] - arr[i + 1] + 1)
        arr[i] = minOf(arr[i], arr[i + 1] - 1)
    }
    println(result)
}