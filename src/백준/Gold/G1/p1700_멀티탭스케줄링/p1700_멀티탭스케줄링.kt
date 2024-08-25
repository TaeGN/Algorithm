package 백준.Gold.G1.p1700_멀티탭스케줄링

data class Flag(private val first: Long = 0, private val second: Long = 0) {
    fun contains(num: Int) = ((if (num < 60) first else second) and (1L shl num)) != 0L
    fun add(num: Int) = if (num < 60) copy(first or (1L shl num), second) else copy(first, second or (1L shl num))
    fun remove(num: Int) = if (num < 60) copy(first xor (1L shl num), second) else copy(first, second xor (1L shl num))
    fun countOneBits() = first.countOneBits() + second.countOneBits()
}

fun main() {
    fun result(n: Int, k: Int): Int {
        val dp = Array(k + 1) { mutableMapOf<Flag, Int>() }.apply { this[0][Flag()] = 0 }
        for ((idx, num) in readln().split(" ").map(String::toInt).withIndex()) {
            for ((flag, count) in dp[idx]) {
                if (flag.contains(num)) dp[idx + 1].compute(flag) { _, v -> if (v == null) count else minOf(v, count) }
                else if (flag.countOneBits() < n) dp[idx + 1].compute(flag.add(num)) { _, v -> if (v == null) count else minOf(v, count) }
                else {
                    for(i in 1..k) {
                        if(flag.contains(i)) dp[idx + 1].compute(flag.remove(i).add(num)) { _, v -> if (v == null) count + 1 else minOf(v, count + 1) }
                    }
                }
            }
        }
        return dp.last().values.min()
    }
    val (N, K) = readln().split(" ").map(String::toInt)
    println(result(N, K))
}