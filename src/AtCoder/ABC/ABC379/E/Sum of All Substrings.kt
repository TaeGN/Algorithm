package AtCoder.ABC.ABC379.E

fun main() {
    val N = readln().trim().toInt()
    val S = readln().trim()
    val sumArr = LongArray(N)
    for (i in S.indices) {
        sumArr[i] += S[i].digitToInt().toLong() * (i + 1)
    }
    for (i in 0 until N) {
        sumArr[i] += sumArr.getOrElse(i - 1) { 0 }
    }
    val sb = StringBuilder()
    for (i in (N - 1) downTo 1) {
        sumArr[i - 1] += sumArr[i] / 10
        sb.append(sumArr[i] % 10)
    }
    println("${sumArr[0]}${sb.reverse()}")
}