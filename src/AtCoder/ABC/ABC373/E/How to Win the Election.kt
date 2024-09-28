package AtCoder.ABC.ABC373.E

fun main() {
    val (N, M, K) = readln().trim().split(" ").let { Triple(it[0].toInt(), it[1].toInt(), it[2].toLong()) }
    val input =
        readln().trim().split(" ").map(String::toLong).mapIndexed { index, l -> index to l }.sortedBy { it.second }
    val A = input.map { it.second }
    val aIdx = input.map { it.first }
    fun result(): String {
        if (M == N) return "0 ".repeat(N)
        val sumArr = LongArray(N)
        for (i in 0 until N) {
            sumArr[i] = sumArr.getOrElse(i - 1) { 0 } + A[i]
        }
        val remainedK = K - sumArr.last()

        fun isPossible(idx: Int, k: Long): Boolean {
            val num = A[idx]
            val toIdx = A.binarySearch(num + k + 1).let { if (it >= 0) it else -it - 1 }
            val fromIdx = if (idx < N - M) N - M else N - M - 1
            if (toIdx <= fromIdx) return false
            val sum =
                sumArr.getOrElse(toIdx - 1) { 0 } - sumArr.getOrElse(fromIdx - 1) { 0 } - if (idx in fromIdx until toIdx) num else 0
            val count = toIdx - fromIdx - if (idx in fromIdx until toIdx) 1 else 0
            return (num + k) >= (sum + remainedK - k) / count
        }

        fun search(idx: Int, start: Long = 0, end: Long = remainedK): Long {
            val mid = (start + end) / 2
            if (start == mid) return if (isPossible(idx, start)) start else if (isPossible(idx, end)) end else -1
            return if (isPossible(idx, mid)) search(idx, start, mid)
            else search(idx, mid + 1, end)
        }

        val result = LongArray(N)
        for (i in 0 until N) {
            result[aIdx[i]] = search(i)
        }
        return result.joinToString(" ")
    }
    println(result())
}