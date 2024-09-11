package 백준.Gold.G5.p11758_CCW

fun main() {
    fun ccw(x1: Int, y1: Int, x2: Int, y2: Int, x3: Int, y3: Int): Int =
        (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3)
    val (x1, y1) = readln().split(" ").map(String::toInt)
    val (x2, y2) = readln().split(" ").map(String::toInt)
    val (x3, y3) = readln().split(" ").map(String::toInt)
    println(
        when (ccw(x1, y1, x2, y2, x3, y3)) {
            in 1..Int.MAX_VALUE -> 1
            0 -> 0
            else -> -1
        }
    )
}