package AtCoder.ABC.ABC356.A

fun main() {
    val (N, L, R) = readln().split(" ").map(String::toInt)
    val numArr = IntArray(N) { it + 1 }
    numArr.sortDescending(L - 1, R)
    println(numArr.joinToString(" "))
}