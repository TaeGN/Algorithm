package AtCoder.ABC.ABC351.F

fun main() {
    val N = readln().toInt()
    val A = readln().split(" ").map(String::toInt)
    val sortedA = A.sorted()
    var result = 0L
    for (i in 0 until N) {
        result += sortedA[i].toLong() * i
        result -= A[i].toLong() * (N - 1 - i)
    }
    println(result)
}