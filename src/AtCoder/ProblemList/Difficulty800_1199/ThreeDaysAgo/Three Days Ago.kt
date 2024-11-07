package AtCoder.ProblemList.Difficulty800_1199.ThreeDaysAgo

fun main() {
    val S = readln().trim()
    val prev = LongArray(1 shl 10)
    val next = LongArray(1 shl 10)
    var result = 0L
    for (s in S) {
        for (i in prev.indices) {
            next[i xor (1 shl (s - '0'))] += prev[i]
        }
        for (i in prev.indices) {
            prev[i] = next[i]
            next[i] = 0
        }
        prev[1 shl (s - '0')]++
        result += prev[0]
    }
    println(result)
}