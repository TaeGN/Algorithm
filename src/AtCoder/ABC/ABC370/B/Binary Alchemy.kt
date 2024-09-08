package AtCoder.ABC.ABC370.B

fun main() {
    val N = readln().toInt()
    val matrix = Array(N) { readln().trim().split(" ").map(String::toInt) }
    var elm = 1
    for (i in 1..N) {
        elm = if (elm >= i) matrix[elm - 1][i - 1] else matrix[i - 1][elm - 1]
    }
    println(elm)
}