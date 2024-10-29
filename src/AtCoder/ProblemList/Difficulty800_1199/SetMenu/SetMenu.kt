package AtCoder.ProblemList.Difficulty800_1199.SetMenu

fun main() {
    val (N, M, P) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt).sorted()
    val B = readln().trim().split(" ").map(String::toInt).sorted()
    val sumB = LongArray(B.size)
    for (i in B.indices) {
        sumB[i] = sumB.getOrElse(i - 1) { 0 } + B[i]
    }
    var j = M - 1
    var result = 0L
    for (i in 0 until N) {
        while (j >= 0 && A[i] + B[j] >= P) j--
        result += A[i].toLong() * (j + 1) + sumB.getOrElse(j) { 0 } + P.toLong() * (M - (j + 1))
    }
    println(result)
}