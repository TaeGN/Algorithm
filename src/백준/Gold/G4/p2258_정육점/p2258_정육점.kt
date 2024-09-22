package 백준.Gold.G4.p2258_정육점

const val IMPOSSIBLE = Long.MAX_VALUE shr 2
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val nArr = Array(N) { readln().split(" ").map(String::toInt) }.sortedWith(compareBy({ it[1] }, { -it[0] }))
    fun result(): Long {
        var wSum = 0L
        var costSum = 0L
        var minCost = IMPOSSIBLE
        for (i in nArr.indices) {
            val (w, cost) = nArr[i]
            if (i > 0 && nArr[i - 1][1] < cost) costSum = 0
            wSum += w
            costSum += cost
            if (wSum >= M) minCost = minOf(minCost, costSum)
        }
        return if (minCost == IMPOSSIBLE) -1 else minCost
    }
    println(result())
}