package 백준.Platinum.P3.p10670_MoovieMooving

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (N, L) = readln().trim().split(" ").map(String::toInt)
    val dp = Array(1 shl N) { IMPOSSIBLE }.apply { this[0] = 0 }
    val movieArr = Array(N) { mutableListOf<Int>() }.apply { this[0].add(0) }
    val dArr = IntArray(N)
    for (i in 0 until N) {
        for ((j, num) in readln().trim().split(" ").map(String::toInt).withIndex()) {
            if (j == 0) dArr[i] = num
            else if (j >= 2) movieArr[i].add(num)
        }
    }
    for (flag in dp.indices) {
        for (i in 0 until N) {
            if ((flag and (1 shl i)) == 0) continue
            val pFlag = flag xor (1 shl i)
            val idx = movieArr[i].binarySearch(dp[pFlag] + 1).let { if (it >= 0) it else -it - 1 } - 1
            if (idx >= 0) dp[flag] = minOf(L, maxOf(dp[flag], movieArr[i][idx] + dArr[i]))
        }
    }
    var result = N + 1
    for (flag in dp.indices) {
        if (dp[flag] >= L) result = minOf(result, flag.countOneBits())
    }
    println(if (result == N + 1) -1 else result)
}