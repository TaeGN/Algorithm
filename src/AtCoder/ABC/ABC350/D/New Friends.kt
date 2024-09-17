package AtCoder.ABC.ABC350.D

fun main() {
    val (N, M) = readln().split(" ").map(String::toInt)
    val parents = IntArray(N + 1) { it }
    fun find(id: Int): Int = if (parents[id] == id) id else find(parents[id]).also { parents[id] = it }
    fun union(id1: Int, id2: Int) {
        parents[find(id2)] = find(id1)
    }

    val lineCounts = IntArray(N + 1)
    repeat(M) {
        readln().split(" ").map(String::toInt).let { union(it[0], it[1]); lineCounts[it[0]]++; lineCounts[it[1]]++ }
    }
    val group = mutableMapOf<Int, MutableList<Int>>()
    for (i in 1..N) {
        group.compute(find(i)) { _, v -> v?.also { it.add(i) } ?: mutableListOf(i) }
    }

    val result = group.asSequence()
        .fold(0L) { acc, (_, list) -> acc + list.size.toLong() * (list.size - 1) - list.sumOf { lineCounts[it].toLong() } }
    println(result / 2)
}