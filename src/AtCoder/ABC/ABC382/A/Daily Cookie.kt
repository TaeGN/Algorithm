package AtCoder.ABC.ABC382.A

fun main() {
    val (N, D) = readln().trim().split(" ").map(String::toInt)
    val S = readln().trim()
    println(S.count { it == '.' } + D)
}