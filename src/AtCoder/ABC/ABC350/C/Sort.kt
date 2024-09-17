package AtCoder.ABC.ABC350.C

fun main() {
    val N = readln().toInt()
    val A = readln().split(" ").map { it.toInt() - 1 }.toIntArray()
    val S = IntArray(N)
    for ((i, a) in A.withIndex()) {
        S[a] = i
    }
    val sb = StringBuilder()
    var count = 0
    for (i in 0 until N) {
        val j = S[i]
        if (i == j) continue
        sb.appendLine("${i + 1} ${j + 1}")
        val n1 = A[i]
        val n2 = A[j]
        A[i] = n2
        A[j] = n1
        S[n1] = j
        S[n2] = i
        count++
    }
    println(count)
    println(sb)
}