package 백준.Gold.G3.p16724_피리부는사나이

fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").let { it[0].toInt() to it[1].toInt() }
    val map = mutableListOf<CharArray>()
    repeat(N) {
        map.add(readLine().toCharArray())
    }

    val parentArr = IntArray(N * M) { it }
    fun IntArray.find(idx: Int): Int =
        if (parentArr[idx] == idx) idx
        else find(parentArr[idx]).also { parentArr[idx] = it }

    fun IntArray.union(idx1: Int, idx2: Int): Boolean {
        val root1 = find(idx1)
        val root2 = find(idx2)
        this[root2] = root1
        return root1 != root2
    }

    fun IntArray.union(r1: Int, c1: Int, r2: Int, c2: Int): Boolean = union(r1 * M + c1, r2 * M + c2)

    fun MutableList<CharArray>.move(startR: Int, startC: Int) {
        var r = startR
        var c = startC
        do {
            val prevR = r
            val prevC = c
            when (this[r][c]) {
                'U' -> r -= 1
                'D' -> r += 1
                'L' -> c -= 1
                'R' -> c += 1
            }
        } while (parentArr.union(prevR, prevC, r, c))
    }

    for (r in 0 until N) {
        for (c in 0 until M) {
            map.move(r, c)
        }
    }

    println(parentArr.filterIndexed { index, i -> index == i }.count())
}