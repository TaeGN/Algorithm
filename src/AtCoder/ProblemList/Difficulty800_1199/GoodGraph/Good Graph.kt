package AtCoder.ProblemList.Difficulty800_1199.GoodGraph

fun main() {
    fun IntArray.find(id: Int): Int = if (this[id] == id) id else find(this[id]).also { this[id] = it }
    fun IntArray.union(id1: Int, id2: Int) {
        this[find(id2)] = find(id1)
    }
    val (N, M) = readln().trim().split(" ").map(String::toInt)
    val parent = IntArray(N + 1) { it }
    repeat(M) { readln().trim().split(" ").map(String::toInt).let { parent.union(it[0], it[1]) } }
    val set = mutableSetOf<Pair<Int, Int>>()
    repeat(readln().trim().toInt()) {
        val (x, y) = readln().trim().split(" ").map(String::toInt)
        val px = parent.find(x)
        val py = parent.find(y)
        if (px != py) set.add(minOf(px, py) to maxOf(px, py))
    }
    val sb = StringBuilder()
    repeat(readln().trim().toInt()) {
        val (p, q) = readln().trim().split(" ").map(String::toInt)
        val pp = parent.find(p)
        val pq = parent.find(q)
        sb.appendLine(if (minOf(pp, pq) to maxOf(pp, pq) in set) "No" else "Yes")
    }
    println(sb)
}