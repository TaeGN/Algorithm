package AtCoder.ProblemList.Difficulty400_799.LeftoverRecipes

fun main() {
    val N = readln().trim().toInt()
    val Q = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val B = readln().trim().split(" ").map(String::toInt)
    fun isPossible(count: Int): Boolean {
        for (i in 0..count) {
            var isPossible = true
            for (j in 0 until N) {
                if (A[j].toLong() * i + B[j].toLong() * (count - i) > Q[j]) {
                    isPossible = false
                    break
                }
            }
            if (isPossible) return true
        }
        return false
    }

    fun search(start: Int = 0, end: Int = 2000000): Int {
        val mid = (start + end) / 2
        if (start == mid) return if (isPossible(end)) end else start
        return if (isPossible(mid)) search(mid, end)
        else search(start, mid - 1)
    }
    println(search())
}