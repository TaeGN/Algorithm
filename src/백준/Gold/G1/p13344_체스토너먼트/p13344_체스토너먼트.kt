package 백준.Gold.G1.p13344_체스토너먼트

const val EMPTY = -1
fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val list = mutableListOf<Pair<Int, Int>>()
    val unionParent = IntArray(N) { it }
    fun find(id: Int): Int = if (unionParent[id] == id) id else find(unionParent[id]).also { unionParent[id] = it }
    fun union(id1: Int, id2: Int) {
        unionParent[find(id2)] = find(id1)
    }
    repeat(M) {
        val input = readln().split(" ")
        val A = input[0].toInt()
        val B = input[2].toInt()
        when (input[1]) {
            ">" -> list.add(A to B)
            "<" -> list.add(B to A)
            "=" -> union(A, B)
        }
    }

    fun result(): Boolean {
        val outLists = List(N) { mutableListOf<Int>() }
        for ((A, B) in list) {
            if (find(A) == find(B)) return false
            outLists[find(A)].add(find(B))
        }

        val stack = ArrayDeque<Int>()
        val parentArr = IntArray(N) { EMPTY }
        val sccIdArr = IntArray(N) { EMPTY }
        var sccId = 0
        var id = 0
        var isPossible = true
        fun scc(from: Int): Int {
            parentArr[from] = ++id
            stack.addFirst(from)
            var parent = parentArr[from]
            for (to in outLists[from]) {
                if (parentArr[to] == EMPTY) parent = minOf(parent, scc(to))
                else if (sccIdArr[to] == EMPTY) parent = minOf(parent, parentArr[to])
            }
            if (parent == parentArr[from]) {
                sccId++
                var count = 0
                while (true) {
                    val idx = stack.removeFirst()
                    sccIdArr[idx] = sccId
                    count++
                    if (idx == from) break
                }
                if (count > 1) isPossible = false
            }
            return parent
        }
        for (i in 0 until N) {
            if (sccIdArr[find(i)] == EMPTY) scc(find(i))
        }
        return isPossible
    }

    println(if (result()) "consistent" else "inconsistent")
}