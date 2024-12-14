package AtCoder.ABC.ABC384.B

fun main() {
    val (N, R) = readln().trim().split(" ").map(String::toInt)
    var curR = R
    repeat(N) {
        val (D, A) = readln().trim().split(" ").map(String::toInt)
        if ((D == 1 && curR in 1600..2799) || (D == 2 && curR in 1200..2399)) curR += A
    }
    println(curR)
}