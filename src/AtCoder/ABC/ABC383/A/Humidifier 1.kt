package AtCoder.ABC.ABC383.A

fun main() {
    var prevT = 0
    var prevV = 0
    repeat(readln().trim().toInt()) {
        val (T, V) = readln().trim().split(" ").map(String::toInt)
        prevV = maxOf(prevV - (T - prevT), 0) + V
        prevT = T
    }
    println(prevV)
}