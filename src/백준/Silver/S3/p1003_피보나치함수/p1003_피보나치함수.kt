package 백준.Silver.S3.p1003_피보나치함수

const val MAX_N = 40
fun main() {
    val dp = List(MAX_N + 1) { IntArray(2) }.apply { this[0][0] = 1; this[1][1] = 1 }
    fun fibonacci(n: Int): Pair<Int, Int> {
        if (dp[n][0] != 0 || dp[n][1] != 0) return dp[n][0] to dp[n][1]
        val (firstZeroCount, firstOneCount) = fibonacci(n - 1)
        val (secondZeroCount, secondOneCount) = fibonacci(n - 2)
        return ((firstZeroCount + secondZeroCount) to (firstOneCount + secondOneCount)).also {
            dp[n][0] = it.first; dp[n][1] = it.second
        }
    }

    val sb = StringBuilder()
    val T = readln().toInt()
    repeat(T) {
        sb.appendLine(fibonacci(readln().toInt()).let { "${it.first} ${it.second}" })
    }
    println(sb)
}