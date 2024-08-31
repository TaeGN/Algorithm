package 백준.Gold.G5.p1717_집합의표현

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val parentArr = IntArray(N + 1) { it }
    fun find(idx: Int): Int = if (parentArr[idx] == idx) idx else find(parentArr[idx]).also { parentArr[idx] = it }
    fun isUnion(idx1: Int, idx2: Int) = find(idx1) == find(idx2)
    fun union(idx1: Int, idx2: Int) {
        parentArr[find(idx2)] = find(idx1)
    }

    val sb = StringBuilder()
    repeat(M) {
        val (type, a, b) = readln().trim().split(" ").map(String::toInt)
        if (type == 1) sb.appendLine(if (isUnion(a, b)) "YES" else "NO")
        else union(a, b)
    }
    println(sb)
}