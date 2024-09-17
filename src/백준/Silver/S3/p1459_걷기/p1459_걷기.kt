package 백준.Silver.S3.p1459_걷기

fun main() {
    val (X, Y, W, S) = readln().split(" ").map(String::toInt)
    fun result(): Long = when (S) {
        in 2 * W..Int.MAX_VALUE -> (X + Y).toLong() * W
        in W..2 * W -> minOf(X, Y).toLong() * S + (maxOf(X, Y) - minOf(X, Y)).toLong() * W
        in 0..W -> maxOf(X, Y).toLong() * S + (if ((X + Y) % 2 == 0) 0 else (W - S))
        else -> throw IllegalArgumentException()
    }
    println(result())
}