package 백준.Platinum.P5.p13941_Kronican

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
fun main() {
    fun Int.contains(idx: Int) = (this and (1 shl idx)) != 0
    fun Int.add(idx: Int) = this or (1 shl idx)
    val (N, K) = readln().split(" ").map(String::toInt)
    val matrix = Array(N) { readln().split(" ").map(String::toInt) }
    fun result(): Int {
        val dp = IntArray(1 shl N) { IMPOSSIBLE }.apply { this[0] = 0 }
        for (count in 0 until (N - K)) {
            for (flag in dp.indices) {
                if (flag.countOneBits() != count || dp[flag] == IMPOSSIBLE) continue
                for (from in 0 until N) {
                    if (flag.contains(from)) continue
                    var minWeight = IMPOSSIBLE
                    for (to in 0 until N) {
                        if (from == to || flag.contains(to)) continue
                        minWeight = minOf(minWeight, matrix[from][to])
                    }
                    dp[flag.add(from)] = minOf(dp[flag.add(from)], dp[flag] + minWeight)
                }
            }
        }
        var result = IMPOSSIBLE
        for (flag in dp.indices) {
            if (flag.countOneBits() == (N - K)) result = minOf(result, dp[flag])
        }
        return result
    }
    println(result())
}