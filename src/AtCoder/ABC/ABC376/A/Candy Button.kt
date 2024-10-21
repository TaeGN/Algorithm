package AtCoder.ABC.ABC376.A

fun main() {
    val (N, C) = readln().trim().split(" ").map(String::toInt)
    val T = readln().trim().split(" ").map(String::toInt)
    var prev = -C
    var result = 0
    for (t in T) {
        if (t - prev >= C) {
            result++
            prev = t
        }
    }
    println(result)
}