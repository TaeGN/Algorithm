package AtCoder.ProblemList.Difficulty400_799.AlmostEqual

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val sList = List(N) { readln().trim() }
    fun permutation(idx: Int = 0, flag: Int = 0, idxArr: IntArray = IntArray(N)): Boolean {
        if (idx == N) {
            for (i in 0 until (N - 1)) {
                var count = 0
                for (j in 0 until M) {
                    if (sList[idxArr[i]][j] == sList[idxArr[i + 1]][j]) count++
                }
                if (count != (M - 1)) return false
            }
            return true
        }
        for (i in 0 until N) {
            if ((flag and (1 shl i)) != 0) continue
            idxArr[idx] = i
            if (permutation(idx + 1, flag or (1 shl i), idxArr)) return true
        }
        return false
    }
    println(if (permutation()) "Yes" else "No")
}