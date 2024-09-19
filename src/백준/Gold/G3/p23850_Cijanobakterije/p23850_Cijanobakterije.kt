package 백준.Gold.G3.p23850_Cijanobakterije

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val parentArr = IntArray(N + 1) { it }
    val outLists = List(N + 1) { mutableListOf<Int>() }
    fun find(id: Int): Int = if (parentArr[id] == id) id else find(parentArr[id]).also { parentArr[id] = it }
    fun union(id1: Int, id2: Int) {
        parentArr[find(id2)] = find(id1)
    }
    repeat(M) {
        readln().split(" ").map(String::toInt)
            .let { union(it[0], it[1]); outLists[it[0]].add(it[1]); outLists[it[1]].add(it[0]) }
    }
    var visitedCount = 0
    val visited = IntArray(N + 1)
    fun maxLen(id: Int): Pair<Int, Int> {
        visited[id] = visitedCount
        var maxLen = 0
        var maxLenId = id
        for (nId in outLists[id]) {
            if (visited[nId] == visitedCount) continue
            val (nMaxLen, nMaxLenId) = maxLen(nId)
            if (maxLen < nMaxLen) {
                maxLen = nMaxLen
                maxLenId = nMaxLenId
            }
        }
        return (1 + maxLen) to maxLenId
    }

    val lenArr = IntArray(N + 1)
    for (i in 1..N) {
        val idx = find(i)
        if (lenArr[idx] != 0) continue
        visitedCount++
        val (_, maxLenId) = maxLen(idx)
        visitedCount++
        val (maxLen, _) = maxLen(maxLenId)
        lenArr[idx] = maxLen
    }
    println(lenArr.sum())
}