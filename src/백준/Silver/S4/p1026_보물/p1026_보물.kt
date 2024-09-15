package 백준.Silver.S4.p1026_보물

fun main() {
    val N = readln().toInt()
    val A = readln().split(" ").map(String::toInt).sorted()
    val B = readln().split(" ").map(String::toInt).sortedDescending()
    println((0 until N).fold(0) { acc, i -> acc + A[i] * B[i] })
}