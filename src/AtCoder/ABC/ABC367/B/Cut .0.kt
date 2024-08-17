package AtCoder.ABC.ABC367.B

fun main() {
    val (A, B, C) = readln().split(" ").map(String::toInt)
    if (B >= C) println(if (A in B..24 || A in 0..C) "No" else "Yes")
    else println(if (A in B..C) "No" else "Yes")
}