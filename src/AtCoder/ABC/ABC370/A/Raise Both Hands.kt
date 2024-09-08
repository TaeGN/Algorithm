package AtCoder.ABC.ABC370.A

fun main() {
    val (L, R) = readln().trim().split(" ").map(String::toInt)
    println(
        when {
            L == 1 && R == 0 -> "Yes"
            L == 0 && R == 1 -> "No"
            else -> "Invalid"
        }
    )
}