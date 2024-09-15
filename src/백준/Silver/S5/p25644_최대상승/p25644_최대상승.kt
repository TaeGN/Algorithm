package 백준.Silver.S5.p25644_최대상승

fun main() {
    val N = readln().toInt()
    val A = readln().split(" ").map(String::toInt)
    var max = A.last()
    var result = 0
    for (i in (N - 2) downTo 0) {
        result = maxOf(result, max - A[i])
        max = maxOf(max, A[i])
    }
    println(result)
}