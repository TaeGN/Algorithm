package AtCoder.ABC.ABC368.A

fun main() {
    val (N, K) = readln().split(" ").map(String::toInt)
    val arr = readln().split(" ").map(String::toInt)
    println((arr.subList(N - K, N) + arr.subList(0, N - K)).joinToString(" "))
}