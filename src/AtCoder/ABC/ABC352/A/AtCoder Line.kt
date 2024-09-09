package AtCoder.ABC.ABC352.A

fun main() {
    val (N, X, Y, Z) = readln().trim().split(" ").map(String::toInt)
    println(if (Z in minOf(X, Y)..maxOf(X, Y)) "Yes" else "No")
}