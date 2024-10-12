package AtCoder.ProblemList.Difficulty1000.floorA_i2k

fun main() {
    val sb = StringBuilder()
    fun Int.bitReversal(size: Int): Int {
        var result = 0
        for (i in 0 until size) {
            if ((this and (1 shl i)) != 0) result += (1 shl (size - i - 1))
        }
        return result
    }
    repeat(readln().toInt()) {
        val (N, K) = readln().trim().split(" ").map(String::toInt)
        val max = 1 shl K
        val min = 1 shl (K - 1)
        if (N >= min) repeat(N) { sb.append("${maxOf(1, max - it - 1)} ") }
        else repeat(N) { sb.append("${min + it.bitReversal(K - 1)} ") }
        sb.appendLine()
    }
    println(sb)
}