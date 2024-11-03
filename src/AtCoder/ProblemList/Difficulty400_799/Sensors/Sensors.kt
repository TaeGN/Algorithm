package AtCoder.ProblemList.Difficulty400_799.Sensors

fun main() {
    val (H, W) = readln().trim().split(" ").map(String::toInt)
    val parent = mutableListOf<Int>()
    fun find(id: Int): Int = if (parent[id] == id) id else find(parent[id]).also { parent[id] = it }
    fun union(id1: Int, id2: Int) {
        parent[find(id2)] = find(id1)
    }

    val matrix = Array(H) { IntArray(W) { -1 } }
    for (i in 0 until H) {
        for ((j, type) in readln().trim().withIndex()) {
            if (type == '#') {
                matrix[i][j] = parent.size
                parent.add(parent.size)
                if (i > 0) {
                    if (j > 0 && matrix[i - 1][j - 1] >= 0) union(matrix[i][j], matrix[i - 1][j - 1])
                    if (matrix[i - 1][j] >= 0) union(matrix[i][j], matrix[i - 1][j])
                    if (j < (W - 1) && matrix[i - 1][j + 1] >= 0) union(matrix[i][j], matrix[i - 1][j + 1])
                }
                if (j > 0 && matrix[i][j - 1] >= 0) union(matrix[i][j], matrix[i][j - 1])
            }
        }
    }
    println(parent.groupBy(::find).size)
}