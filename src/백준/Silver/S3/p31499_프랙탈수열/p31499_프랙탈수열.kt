package 백준.Silver.S3.p31499_프랙탈수열

fun main() {
    val (n, m) = readln().split(" ").let { it[0].toInt() to it[1].toInt() }
    (1..n).fold(1L) { acc: Long, i: Int -> (acc * i) % m }.let(::println)
}