package AtCoder.ABC.ABC375.A

fun main() {
    val N = readln().toInt()
    val S = readln()
    var result = 0
    for (i in 0 until (N - 2)) {
        if (S[i] == '#' && S[i + 1] == '.' && S[i + 2] == '#') result++
    }
    println(result)
}