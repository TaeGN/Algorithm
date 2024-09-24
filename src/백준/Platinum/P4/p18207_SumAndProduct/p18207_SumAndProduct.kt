package 백준.Platinum.P4.p18207_SumAndProduct

fun main() {
    val N = readln().toInt()
    val nArr = readln().trim().split(" ").map(String::toInt)
    val oneCount = IntArray(N)
    val notOneIdxList = mutableListOf<Int>()
    for ((idx, n) in nArr.withIndex()) {
        oneCount[idx] = oneCount.getOrElse(idx - 1) { 0 } + if (n == 1) 1 else 0
        if (n > 1) notOneIdxList.add(idx)
    }
    val totalOneCount = oneCount.last()
    var result = 0L
    for (i in notOneIdxList.indices) {
        val l = notOneIdxList[i]
        var sum = nArr[l]
        var mul = nArr[l].toLong()
        for (j in (i + 1) until notOneIdxList.size) {
            val r = notOneIdxList[j]
            sum += nArr[r]
            mul *= nArr[r]
            if (mul - sum > totalOneCount) break
            val insideOneCount = oneCount[r] - oneCount[l]
            val diff = (mul - (sum + insideOneCount)).toInt()
            val leftOneCount = oneCount[l] - oneCount.getOrElse(notOneIdxList.getOrElse(i - 1) { -1 }) { 0 }
            val rightOneCount =
                oneCount.getOrElse(notOneIdxList.getOrElse(j + 1) { -1 }) { totalOneCount } - oneCount[r]
            val outsideOneCount = leftOneCount + rightOneCount
            result += maxOf(0, minOf(leftOneCount, rightOneCount, diff, outsideOneCount - diff) + 1)
        }
    }
    println(result)
}