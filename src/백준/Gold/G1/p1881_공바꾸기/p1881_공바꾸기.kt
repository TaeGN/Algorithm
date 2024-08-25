package 백준.Gold.G1.p1881_공바꾸기

const val IMPOSSIBLE = Int.MAX_VALUE shr 2
const val FLAG_SIZE = 1 shl 10
fun main() {
    fun Int.contains(num: Int) = (this and (1 shl num)) != 0
    fun Int.add(num: Int) = this or (1 shl num)
    fun Int.remove(num: Int) = this xor (1 shl num)
    fun result(n: Int): Int {
        if (n == 0) return 0
        val dp = Array(n + 1) { IntArray(FLAG_SIZE) { IMPOSSIBLE } }.apply { this[0][0] = 0 }
        for ((idx, num) in readln().split(" ").map(String::toInt).withIndex()) {
            for (flag in 0 until FLAG_SIZE) {
                if (dp[idx][flag] == IMPOSSIBLE) continue
                if (flag.contains(num)) dp[idx + 1][flag] = minOf(dp[idx + 1][flag], dp[idx][flag])
                else if (flag.countOneBits() < 4) dp[idx + 1][flag.add(num)] =
                    minOf(dp[idx + 1][flag.add(num)], dp[idx][flag] + 1)
                else {
                    for (i in 0..9) {
                        if (flag.contains(i)) dp[idx + 1][flag.remove(i).add(num)] =
                            minOf(dp[idx + 1][flag.remove(i).add(num)], dp[idx][flag] + 1)
                    }
                }
            }
        }
        return dp.last().min()
    }
    println(result(readln().toInt()))
}