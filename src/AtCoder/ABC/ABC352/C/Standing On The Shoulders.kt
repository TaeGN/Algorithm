package AtCoder.ABC.ABC352.C

fun main() {
    val N = readln().toInt()
    var maxValue = 0
    val result = Array(N) { readln().trim().split(" ").map(String::toInt) }
        .fold(0L) { acc, ints -> acc + ints.also { maxValue = maxOf(maxValue, it[1] - it[0]) }[0] }
    println(result + maxValue)
}