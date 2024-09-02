package Codeforces.Div3.Round970.D

const val EMPTY = -1
fun main() {
    val sb = StringBuilder()
    val sccIdArr = IntArray(200001)
    val countArr = IntArray(200001)
    val sccCountArr = IntArray(200001)
    val parentArr = IntArray(200001)
    val pArr = IntArray(200001)
    val stateArr = CharArray(200001)
    val visited = BooleanArray(200001)
    repeat(readln().toInt()) {
        val N = readln().toInt()
        countArr.fill(EMPTY, 1, N + 1)
        sccCountArr.fill(0, 1, N + 1)
        sccIdArr.fill(EMPTY, 1, N + 1)
        parentArr.fill(EMPTY, 1, N + 1)
        readln().trim().split(" ").map(String::toInt).forEachIndexed { index, i -> pArr[index + 1] = i }
        readln().forEachIndexed { index, c -> stateArr[index + 1] = c }
        val stack = ArrayDeque<Int>()
        var id = 0
        var sccId = 0
        fun scc(from: Int): Int {
            parentArr[from] = ++id
            stack.addFirst(from)
            var parent = parentArr[from]
            val to = pArr[from]
            if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
            else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
            if (parent == parentArr[from]) {
                sccId++
                while (true) {
                    val curIdx = stack.removeFirst()
                    sccIdArr[curIdx] = id
                    if (curIdx == from) break
                }
            }
            return parent
        }
        for (i in 1..N) {
            if (sccIdArr[i] == EMPTY) scc(i)
        }
        for (i in 1..N) {
            if (stateArr[i] == '0') sccCountArr[sccIdArr[i]]++
        }
        fun dfs(from: Int): Int {
            val fromSccId = sccIdArr[from]
            if (countArr[fromSccId] != EMPTY) return countArr[fromSccId]
            visited[fromSccId] = true
            var count = sccCountArr[fromSccId]
            val to = pArr[from]
            val toSccId = sccIdArr[to]
            if (!visited[toSccId]) count += dfs(to)
            visited[fromSccId] = false
            return count.also { countArr[fromSccId] = it }
        }
        for (i in 1..N) {
            sb.append("${dfs(i)} ")
        }
        sb.appendLine()
    }
    println(sb)
}