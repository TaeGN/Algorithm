package 백준.Silver.S4.p11399_ATM

fun main() {
    val N = readln().toInt()
    val P = readln().split(" ").map(String::toInt).sorted()
    println(P.foldIndexed(0) { index, acc, i -> acc + (N - index) * i })
}