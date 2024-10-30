package AtCoder.ProblemList.Difficulty400_799.GoodTupleProblem

const val EMPTY = 0
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val A = readln().trim().split(" ").map(String::toInt)
    val B = readln().trim().split(" ").map(String::toInt)
    val outLists = List(N + 1) { mutableListOf<Int>() }
    for (i in 0 until M) {
        outLists[A[i]].add(B[i])
        outLists[B[i]].add(A[i])
    }
    val X = IntArray(N + 1)
    fun dfs(from: Int): Boolean {
        for (to in outLists[from]) {
            if (X[to] != EMPTY) {
                if (X[from] == X[to]) return false
                continue
            }
            X[to] = -X[from]
            if (!dfs(to)) return false
        }
        return true
    }

    fun result(): String {
        for (i in 0 until M) {
            if (X[A[i]] == EMPTY) {
                X[A[i]] = 1
                if (!dfs(A[i])) return "No"
            }
        }
        return "Yes"
    }

    println(result())
}