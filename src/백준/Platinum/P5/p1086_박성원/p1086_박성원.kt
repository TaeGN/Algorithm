package 백준.Platinum.P5.p1086_박성원

fun main() {
    fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    val N = readln().toInt()
    val nArr = Array(N) { readln() }
    val K = readln().toInt()
    val remain10PowArr = IntArray(51).apply { this[0] = 1 }
    repeat(remain10PowArr.size - 1) { idx -> remain10PowArr[idx + 1] = (remain10PowArr[idx] * 10) % K }
    fun String.remain(): Int {
        var result = 0
        for (i in 0..(length - 1) / 5) {
            result = (result + (remain10PowArr[i * 5] *
                    (substring(maxOf(0, length - (i + 1) * 5), length - i * 5).toInt() % K))) % K
        }
        return result
    }

    val remainArr = IntArray(N) { nArr[it].remain() }
    val dp = Array(1 shl N) { LongArray(K) }.apply { this[0][0] = 1 }
    for (nFlag in dp.indices) {
        for (i in 0 until N) {
            if ((nFlag and (1 shl i)) == 0) continue
            val flag = nFlag xor (1 shl i)
            for (k in 0 until K) {
                val nk = (k * remain10PowArr[nArr[i].length] + remainArr[i]) % K
                dp[nFlag][nk] += dp[flag][k]
            }
        }
    }
    fun result(): String {
        val top = dp.last().first()
        if (top == 0L) return "0/1"
        val down = (1..N).fold(1L) { acc, i -> acc * i }
        val gcd = gcd(down, top)
        return "${top / gcd}/${down / gcd}"
    }
    println(result())
}