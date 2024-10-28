package AtCoder.ProblemList.Difficulty800_1199.Pyramid

fun main() {
    val N = readln().trim().toInt()
    val A = readln().trim().split(" ").map(String::toInt)
    val forwardCountArr = IntArray(N)
    var forwardCount = 0
    for (i in 0 until N) {
        if (A[i] < ++forwardCount) forwardCount = A[i]
        forwardCountArr[i] = forwardCount
    }
    val backwardCountArr = IntArray(N)
    var backwardCount = 0
    for (i in (N - 1) downTo 0) {
        if (A[i] < ++backwardCount) backwardCount = A[i]
        backwardCountArr[i] = backwardCount
    }
    println((0 until N).maxOf { minOf(forwardCountArr[it], backwardCountArr[it]) })
}