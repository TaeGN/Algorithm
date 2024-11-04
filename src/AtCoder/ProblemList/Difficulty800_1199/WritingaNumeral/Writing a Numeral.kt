package AtCoder.ProblemList.Difficulty800_1199.WritingaNumeral

const val MOD = 998244353
fun main() {
    val queue = ArrayDeque<Int>()
    queue.add(1)
    var q = 1L
    val Q = readln().trim().toInt()
    val pow10 = IntArray(Q + 1).apply { this[0] = 1 }
    for (i in 1..Q) {
        pow10[i] = (pow10[i - 1].toLong() * 10 % MOD).toInt()
    }
    val sb = StringBuilder()
    repeat(Q) {
        val input = readln().trim().split(" ").map(String::toInt)
        when (input.first()) {
            1 -> {
                q = (q * 10 + input.last()) % MOD
                queue.add(input.last())
            }

            2 -> q = (q + MOD - (queue.removeFirst().toLong() * pow10[queue.size] % MOD)) % MOD
            3 -> sb.appendLine(q)
        }
    }
    println(sb)
}