package AtCoder.ProblemList.Difficulty800_1199.SpoonTakingProblem

const val MOD = 998244353
fun main() {
    val N = readln().trim().toInt()
    val P = readln().trim().split(" ").map(String::toInt)
    val S = readln()
    fun result(): Int {
        var result = 0
        val visited = BooleanArray(N)
        var count = 1
        for (i in 0 until N) {
            val idx = P[i] - 1
            visited[idx] = true
            if (S[idx] == 'R' && !visited[(idx + 1) % N]) {
                count = 0
                break
            } else if (S[idx] == '?' && visited[(idx + 1) % N]) count = (count.toLong() * 2 % MOD).toInt()
        }
        result += count
        count = 1
        visited.fill(false)
        for (i in 0 until N) {
            val idx = P[i] - 1
            visited[(idx + 1) % N] = true
            if (S[idx] == 'L' && !visited[idx]) {
                count = 0
                break
            } else if (S[idx] == '?' && visited[idx]) count = (count.toLong() * 2 % MOD).toInt()
        }
        result = (result + count) % MOD
        return result
    }
    println(result())
}