package AtCoder.ABC.ABC373.A

fun main() {
    var result = 0
    repeat(12) { idx -> if (readln().length == idx + 1) result++ }
    println(result)
}