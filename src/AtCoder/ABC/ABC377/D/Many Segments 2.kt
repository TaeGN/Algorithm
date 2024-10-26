package AtCoder.ABC.ABC377.D

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val L = IntArray(M + 1) { 1 }
    repeat(N) {
        val (l, r) = readln().trim().split(" ").map(String::toInt)
        L[r] = maxOf(L[r], l + 1)
    }
    for (r in 1..M) {
        L[r] = maxOf(L[r], L[r - 1])
    }
    var result = 0L
    for (r in 1..M) {
        result += maxOf(0, r - L[r] + 1)
    }
    println(result)
}