package AtCoder.ABC.ABC352.E

fun main() {
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val kLists = mutableListOf<Pair<Int, IntArray>>()
    repeat(M) {
        val (K, C) = readln().trim().split(" ").map(String::toInt)
        kLists.add(C to readln().trim().split(" ").map(String::toInt).toIntArray())
    }

    val parentArr = IntArray(N + 1) { it }
    fun find(id: Int): Int = if (parentArr[id] == id) id else find(parentArr[id]).also { parentArr[id] = it }
    fun union(id1: Int, id2: Int): Boolean {
        if (find(id1) == find(id2)) return false
        parentArr[find(id2)] = find(id1)
        return true
    }

    kLists.sortBy { it.first }
    var result = 0L
    for (kList in kLists) {
        val (C, idArr) = kList
        val id1 = idArr[0]
        for (id2 in idArr) {
            if (union(id1, id2)) result += C
        }
    }

    fun result(): Long {
        for (i in 1..N) {
            find(i)
        }
        val root = parentArr[1]
        for (i in 1..N) {
            if (root != parentArr[i]) return -1
        }
        return result
    }
    println(result())
}