package AtCoder.ABC.ABC363.D

fun main() {
    var curCount = 9L
    val countSumArr = LongArray(36).apply { this[1] = 10 }
    for (i in 2 until countSumArr.size) {
        if (i % 2 == 1) curCount *= 10
        countSumArr[i] = countSumArr[i - 1] + curCount
    }
    fun result(N: Long): String {
        val idx = countSumArr.binarySearch(N).let { if (it >= 0) it else -it - 1 }
        val sb = StringBuilder()
        var count = N - countSumArr[idx - 1] - 1
        if (idx == 1) return "$count"
        for(i in 0 until (idx + 1) / 2 - 1) {
            sb.append(count % 10)
            count /= 10
        }
        sb.append(count % 9 + 1)
        val backward = sb.toString()
        val forward = sb.reverse().toString()
        return if (idx % 2 == 0) forward + backward
        else forward + backward.substring(1)
    }
    println(result(readln().toLong()))
}