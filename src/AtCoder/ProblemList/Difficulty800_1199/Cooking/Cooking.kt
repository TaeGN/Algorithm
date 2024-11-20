package AtCoder.ProblemList.Difficulty800_1199.Cooking

fun main() {
    val N = readln().trim().toInt()
    val T = readln().trim().split(" ").map(String::toInt)
    val total = T.sum()
    val isPossible = BooleanArray(total / 2 + 1).apply { this[0] = true }
    for (t in T) {
        for (i in (isPossible.size - 1) downTo t) {
            isPossible[i] = isPossible[i] or isPossible[i - t]
        }
    }
    fun result(): Int {
        for (i in (isPossible.size - 1) downTo 0) {
            if (isPossible[i]) return total - i
        }
        return -1
    }
    println(result())
}