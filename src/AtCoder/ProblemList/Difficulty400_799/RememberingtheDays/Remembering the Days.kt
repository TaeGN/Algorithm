package AtCoder.ProblemList.Difficulty400_799.RememberingtheDays

const val IMPOSSIBLE = Int.MIN_VALUE shr 2
fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val distMatrix = Array(N + 1) { IntArray(N + 1) { IMPOSSIBLE } }
    repeat(M) {
        val (A, B, C) = readln().trim().split(" ").map(String::toInt)
        distMatrix[A][B] = maxOf(distMatrix[A][B], C)
        distMatrix[B][A] = maxOf(distMatrix[B][A], C)
    }
    var result = 0L
    fun permutation(idx: Int = 0, flag: Int = 0, idxArr: IntArray = IntArray(N)) {
        if (idx == N) {
            var prev = idxArr[0]
            var dist = 0L
            for (i in 1 until N) {
                val next = idxArr[i]
                if (distMatrix[prev][next] == IMPOSSIBLE) break
                dist += distMatrix[prev][next]
                prev = next
            }
            result = maxOf(result, dist)
            return
        }
        for (i in 1..N) {
            if ((flag and (1 shl i) != 0)) continue
            idxArr[idx] = i
            permutation(idx + 1, flag or (1 shl i), idxArr)
        }
    }
    permutation()
    println(result)
}