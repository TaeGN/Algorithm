package AtCoder.ProblemList.Difficulty1200_1599.plus1AndMinus1

fun main() {
    val sb = StringBuilder()
    fun Pair<Int, Long>.count() = second / first + (if (second % first == 0L) 0 else 1)
    repeat(readln().trim().toInt()) {
        val N = readln().trim().toInt()
        val A = readln().trim().split(" ").map(String::toInt)
        val stack = ArrayDeque<Pair<Int, Long>>()
        var count = 1
        var sum = A[0].toLong()
        for (i in 1 until N) {
            if ((sum / count + (if (sum % count == 0L) 0 else 1)) <= A[i]) {
                count++
                sum += A[i]
            } else {
                while (stack.isNotEmpty() && stack.first().count() <= sum / count) {
                    val (pCount, pSum) = stack.removeFirst()
                    count += pCount
                    sum += pSum
                }
                stack.addFirst(count to sum)
                count = 1
                sum = A[i].toLong()
            }
        }
        while (stack.isNotEmpty() && stack.first().count() <= sum / count) {
            val (pCount, pSum) = stack.removeFirst()
            count += pCount
            sum += pSum
        }
        sb.appendLine(if (stack.isEmpty()) "Yes" else "No")
    }
    println(sb)
}