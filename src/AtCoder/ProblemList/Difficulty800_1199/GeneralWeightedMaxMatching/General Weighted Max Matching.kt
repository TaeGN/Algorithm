package AtCoder.ProblemList.Difficulty800_1199.GeneralWeightedMaxMatching

import kotlin.math.abs

fun main() {
    val N = readln().trim().toInt()
    val distMatrix = Array(N) { IntArray(N) }
    repeat(N - 1) { i ->
        val D = readln().trim().split(" ").map(String::toInt)
        for ((j, d) in D.withIndex()) {
            distMatrix[i][j + i + 1] = d
            distMatrix[j + i + 1][i] = d
        }
    }
    var result = 0L
    fun dfs(idx: Int = 0, flag: Int = 0, idxArr: IntArray = IntArray(N)) {
        if (abs(idx - N) <= 1) {
            var curResult = 0L
            for (i in 0 until idx step 2) {
                curResult += distMatrix[idxArr[i]][idxArr[i + 1]]
            }
            result = maxOf(result, curResult)
            return
        }
        for (i in 0 until N) {
            if ((flag and (1 shl i)) == 0) {
                idxArr[idx] = i
                for (j in (i + 1) until N) {
                    if ((flag and (1 shl j)) == 0) {
                        idxArr[idx + 1] = j
                        dfs(idx + 2, flag or (1 shl i) or (1 shl j), idxArr)
                    }
                }
                break
            }
        }
    }
    if (N % 2 == 1) {
        for (i in 0 until N) {
            dfs(flag = (1 shl i))
        }
    } else dfs()
    println(result)
}