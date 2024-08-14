package AtCoder.ABC.ABC359.A

fun main() {
    val N = readln().toInt()
    var result = 0
    repeat(N) {
        if (readln() == "Takahashi") result++
    }
    println(result)
}