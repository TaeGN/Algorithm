package 백준.Platinum.P5.p14278_블록쌓기

const val MOD = 1000000007
fun main() {
    val (W, H) = readln().split(" ").map(String::toInt)
    val size = 1 shl W
    val dp = Array(H + 1) { IntArray(size) }.apply { this[0][size - 1] = 1 }
    val count = IntArray(size)
    for (h in 1..H) {
        for (pFlag in 0 until size) {
            if (dp[h - 1][pFlag] == 0) continue
            count.fill(0)
            count[0] = 1
            for (flag in 0 until size) {
                for (i in (W - 1) downTo 0) {
                    if ((flag and (1 shl i)) != 0) {
                        if ((pFlag and (1 shl i)) != 0) {
                            var nFlag = flag xor (1 shl i)
                            count[flag] = (count[flag] + count[nFlag]) % MOD
                            if (i > 0 && (flag and (1 shl (i - 1))) != 0) {
                                nFlag = nFlag xor (1 shl (i - 1))
                                if ((pFlag and (1 shl (i - 1))) != 0) count[flag] = (count[flag] + count[nFlag]) % MOD
                                if (i > 1 && (flag and (1 shl (i - 2))) != 0 && (pFlag and (1 shl (i - 2))) != 0) {
                                    nFlag = nFlag xor (1 shl (i - 2))
                                    count[flag] = (count[flag] + count[nFlag]) % MOD
                                }
                            }
                        }
                        break
                    }
                }
            }
            for (flag in 0 until size) {
                dp[h][flag] = (dp[h][flag] + dp[h - 1][pFlag].toLong() * count[flag] % MOD).toInt() % MOD
            }
        }
    }
    println(dp[H].fold(0L) { acc, i -> (acc + i) % MOD })
}