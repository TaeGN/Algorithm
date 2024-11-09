package AtCoder.ABC.ABC379.B

fun main() {
    val (N, K) = readln().trim().split(" ").map(String::toInt)
    val S = readln().trim()
    var result = 0
    var count = 0
    for (c in S) {
        if (c == 'O') {
            if (++count == K) {
                result++
                count = 0
            }
        } else count = 0
    }
    println(result)
}