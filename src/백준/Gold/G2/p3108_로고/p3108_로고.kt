package 백준.Gold.G2.p3108_로고

const val EMPTY = -1
fun main() {
    val N = readln().toInt()
    val parentArr = IntArray(N + 1) { it }
    fun IntArray.find(id: Int): Int = if (this[id] == id) id else find(parentArr[id]).also { parentArr[id] = it }
    fun IntArray.union(id1: Int, id2: Int) {
        parentArr[find(id2)] = find(id1)
    }

    val matrix = Array(1001) { IntArray(1001) { EMPTY } }.apply { this[500][500] = N }
    fun Array<IntArray>.get(r: Int, c: Int) = this[r + 500][c + 500]
    fun Array<IntArray>.set(r: Int, c: Int, value: Int) {
        this[r + 500][c + 500] = value
    }

    fun Array<IntArray>.add(r: Int, c: Int, id: Int) {
        val pId = get(r, c)
        if (pId != EMPTY) parentArr.union(pId, id)
        set(r, c, id)
    }

    repeat(N) { id ->
        val (x1, y1, x2, y2) = readln().split(" ").map(String::toInt)
        for (x in x1..x2) {
            matrix.add(x, y1, id)
            matrix.add(x, y2, id)
        }
        for (y in y1..y2) {
            matrix.add(x1, y, id)
            matrix.add(x2, y, id)
        }
    }
    println(parentArr.filterIndexed { index, i -> index == i }.count() - 1)
}