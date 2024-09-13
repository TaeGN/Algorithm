package 백준.Silver.S3.p2134_창고이전

fun main() {
    val (N, M, K) = readln().split(" ").map(String::toInt)
    val nArr = IntArray(N) { readln().toInt() }
    val mArr = IntArray(M) { readln().toInt() }
    val totalCount = minOf(nArr.sum(), mArr.sum())
    fun cost(arr: IntArray): Long {
        var cost = 0L
        var count = 0
        for (i in arr.indices) {
            val curCount = minOf(arr[i], totalCount - count)
            cost += curCount * (i + 1)
            count += curCount
            if (count == totalCount) break
        }
        return cost
    }

    val totalCost = cost(nArr) + cost(mArr)
    println("$totalCount $totalCost")
}