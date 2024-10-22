package AtCoder.ProblemList.Difficulty400_799.OneTimeSwap

fun main() {
    val S = readln().trim()
    fun result(): Long {
        val idxLists = List(26) { ArrayDeque<Int>() }
        for (i in S.indices) {
            idxLists[S[i] - 'a'].add(i)
        }
        var result = if (idxLists.any { it.size > 1 }) 1L else 0L
        for (i in S.indices) {
            idxLists[S[i] - 'a'].removeFirst()
            result += S.length - 1 - i - idxLists[S[i] - 'a'].size
        }
        return result
    }
    println(result())
}