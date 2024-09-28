package 백준.Gold.G3.p1082_방번호

fun main() {
    val N = readln().toInt()
    val P = readln().trim().split(" ").map(String::toInt)
    val M = readln().toInt()
    var minPrice = Int.MAX_VALUE shr 2
    var nonZeroMinPrice = Int.MAX_VALUE shr 2
    var minPriceNum = 0
    var nonZeroMinPriceNum = 0
    for ((i, p) in P.withIndex()) {
        if (minPrice >= p) {
            minPrice = p
            minPriceNum = i
        }
        if (i > 0 && nonZeroMinPrice >= p) {
            nonZeroMinPrice = p
            nonZeroMinPriceNum = i
        }
    }
    fun result(): String {
        val list = mutableListOf<Int>()
        if (M < nonZeroMinPrice) return "0"
        list.add(nonZeroMinPriceNum)
        var m = nonZeroMinPrice
        while (m + minPrice <= M) {
            list.add(minPriceNum)
            m += minPrice
        }
        for (i in list.indices) {
            var isPossible = false
            for (otherNum in (N - 1) downTo (list[i] + 1)) {
                val prevNum = list[i]
                if (m - P[prevNum] + P[otherNum] <= M) {
                    isPossible = true
                    m = m - P[prevNum] + P[otherNum]
                    list[i] = otherNum
                    break
                }
            }
            if (i > 0 && !isPossible) break
        }
        return list.joinToString("")
    }
    println(result())
}