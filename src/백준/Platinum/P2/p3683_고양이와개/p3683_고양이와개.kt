package 백준.Platinum.P2.p3683_고양이와개

const val EMPTY = -1
fun main() {
    val sb = StringBuilder()
    repeat(readln().toInt()) {
        val (c, d, v) = readln().split(" ").map(String::toInt)
        val catGroup = mutableListOf<Pair<Int, Int>>()
        val dogGroup = mutableListOf<Pair<Int, Int>>()
        repeat(v) {
            readln().split(" ").let {
                (if (it[0].first() == 'C') catGroup else dogGroup)
                    .add(it[0].substring(1).toInt() to it[1].substring(1).toInt())
            }
        }
        val outLists = List(catGroup.size) { mutableListOf<Int>() }
        for ((cIdx, catPair) in catGroup.withIndex()) {
            for ((dIdx, dogPair) in dogGroup.withIndex()) {
                if (catPair.first == dogPair.second || catPair.second == dogPair.first) outLists[cIdx].add(dIdx)
            }
        }
        val visited = BooleanArray(dogGroup.size)
        val cIdArr = IntArray(dogGroup.size) { EMPTY }
        fun dfs(cId: Int): Boolean {
            for (dId in outLists[cId]) {
                if (visited[dId]) continue
                visited[dId] = true
                if (cIdArr[dId] == EMPTY || dfs(cIdArr[dId])) {
                    cIdArr[dId] = cId
                    return true
                }
            }
            return false
        }

        var result = v
        for (cId in catGroup.indices) {
            visited.fill(false)
            if (dfs(cId)) result--
        }
        sb.appendLine(result)
    }
    println(sb)
}