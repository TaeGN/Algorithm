package AtCoder.ABC.ABC357.E

const val EMPTY = -1
fun main() {
    val N = readln().toInt()
    val aArr = ("0 " + readln()).split(" ").map(String::toInt)
    val parentArr = IntArray(N + 1) { EMPTY }
    val sccIdArr = IntArray(N + 1) { EMPTY }
    val sccCountList = mutableListOf<Int>()
    val stack = ArrayDeque<Int>()
    var id = 0
    var sccId = 0
    fun scc(from: Int): Int {
        parentArr[from] = ++id
        stack.addFirst(from)
        var parent = parentArr[from]
        val to = aArr[from]
        if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
        else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
        if (parent == parentArr[from]) {
            var count = 0
            while (true) {
                val idx = stack.removeFirst()
                sccIdArr[idx] = sccId
                count++
                if (idx == from) break
            }
            sccCountList.add(count)
            sccId++
        }
        return parent
    }
    for (i in 1..N) {
        if (sccIdArr[i] == EMPTY) scc(i)
    }

    val dp = IntArray(N + 1) { EMPTY }
    fun dp(from: Int): Int {
        if (dp[from] != EMPTY) return dp[from]
        if (from == aArr[from]) return 1.also { dp[from] = it }
        val sccCount = sccCountList[sccIdArr[from]]
        if (sccCount == 1) return (1 + dp(aArr[from])).also { dp[from] = it }
        return sccCount.also { dp[from] = it }
    }

    var result = 0L
    for (i in 1..N) {
        result += dp(i)
    }
    println(result)
}