package AtCoder.ABC.ABC354.A

fun main() {
    val H = readln().toInt()
    var count = 0
    var value = 1L
    var h = 0L
    while (h <= H) {
        h += value
        value *= 2
        count++
    }
    println(count)
}