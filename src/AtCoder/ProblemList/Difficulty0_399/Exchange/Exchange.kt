package AtCoder.ProblemList.Difficulty0_399.Exchange


fun main() {
    val A = readln().trim().split(" ").map(String::toInt).toIntArray()
    val N = readln().trim().toInt()
    val X = readln().trim().split(" ").map(String::toInt)
    val price = intArrayOf(1, 5, 10, 50, 100, 500)
    fun result(): String {
        for (x in X) {
            var remainedX = x
            for (i in (A.size - 1) downTo 0) {
                val count = minOf(A[i], remainedX / price[i])
                remainedX -= price[i] * count
                A[i] -= count
            }
            if (remainedX != 0) return "No"
        }
        return "Yes"
    }
    println(result())
}